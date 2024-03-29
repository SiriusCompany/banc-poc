package com.sirius.io.sob0m01000.service;

import com.alibaba.fastjson.JSON;
import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.common.tuples.Tuple2;
import com.multiverse.eventkit.kit.client.ResponseMeta;
import com.multiverse.eventkit.kit.client.mesh.DefaultRequest;
import com.multiverse.eventkit.kit.contexts.HandlerContext;
import com.multiverse.eventkit.kit.enums.CodecType;
import com.sirius.io.constant.ErrorCode;
import com.sirius.io.model.*;
import com.sirius.io.sob0m01000.downstream.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.multiverse.eventkit.common.constant.ErrorCode.SYSTEM_INTERNAL_ERROR;

@Component
public class ServiceOB0M1000 {
    private final Logger logger = LoggerFactory.getLogger(ServiceOB0M1000.class);

    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private DownstreamWS000001 downstreamWS000001;
    @Autowired
    private DownstreamWS000002 downstreamWS000002;
    @Autowired
    private DownstreamOB1M1001 downstreamOB1M1001;
    @Autowired
    private DownstreamOB9M1001 downstreamOB9M1001;
    @Autowired
    private DownstreamOB9M1002 downstreamOB9M1002;


    public Tuple2<OB0M1000Response, Map<String,String>> handleOB0M1000(Map<String,String> requestHeader, OB0M1000Request ob0M1000Request) throws ServiceException {
        OB0M1000Response OB0M1000Response = new OB0M1000Response();
        String accountID = "";
        String retCode = "0";
        String retMessage = "";
        String transactionID =  requestHeader.get("transactionId");
        Long balance = null;
        if (null == transactionID) {
            HandlerContext handlerContext = HandlerContext.getContext();
            transactionID = handlerContext.getSpanContext().getTraceId();
        }
        try {
            // 1. call BTS sob1m01001 to check the amount whether exceed the limit
            OB1M1001Response ob1M1001Response =  this.callSob1m01001(ob0M1000Request.getTransactionAmount());
            if (!ob1M1001Response.isOk()) {
                throw new ServiceException("Banc000002", "The transaction amount:%d exceed the limit", ob0M1000Request.getTransactionAmount());
            }

            // 2. call DAS sob9m01001 get account id by user id
            OB9M1001Response ob9M1001Response = this.callSob9m01001(ob0M1000Request.getIdUser());
            accountID = ob9M1001Response.getIdAccount();


            // 3. call https://test-sirius.auth.us-east-1.amazoncognito.com/oauth2/token get token
            String accessToken = this.getToken(ob0M1000Request);
            this.logger.info("The response token is:[{}]", accessToken);

            // 4. call https://iolc7ic3g2.execute-api.us-east-1.amazonaws.com/dev/micro-accounts/api/consignar to get account detail
            WS000002Response ws000002Response = this.accountBalanceAccumulation(transactionID, accountID, ob0M1000Request.getTransactionAmount(), accessToken);
            balance = ws000002Response.getBalance();
            if (!StringUtils.isAllEmpty(ws000002Response.getError())) {
                throw new ServiceException(SYSTEM_INTERNAL_ERROR, ws000002Response.getError());
            }
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                ServiceException se = (ServiceException) e;
                retCode = se.getErrorCode();
                retMessage = se.getMessage();
            } else {
                retCode = SYSTEM_INTERNAL_ERROR;
                retMessage = e.getMessage();
            }
            throw e;
        } finally {
            // call DAS sob9m01002 to save transaction history
            OB9M1002Request ob9M1002Request = new OB9M1002Request();
            ob9M1002Request.setTransactionId(transactionID);
            ob9M1002Request.setIdUser(ob0M1000Request.getIdUser());
            ob9M1002Request.setIdAccount(accountID);
            ob9M1002Request.setTransactionAmount(ob0M1000Request.getTransactionAmount());
            if (null != balance) {
                ob9M1002Request.setBalance(balance);
            } else {
                ob9M1002Request.setBalance(null);
            }
            ob9M1002Request.setRetCode(retCode);
            ob9M1002Request.setRetMessage(retMessage);


            this.downstreamOB9M1002.callOB9M1002(null, ob9M1002Request);
        }

        OB0M1000Response.setIdAccount(accountID);
        OB0M1000Response.setBalance(balance);

        return new Tuple2<>(OB0M1000Response, null);
    }


    private String getToken(OB0M1000Request ob0M1000Request){
        try {
            String requestContent = String.format("grant_type=%s&client_id=%s&client_secret=%s&scope=%s",
                    URLEncoder.encode(config.getConfigInc().getString("auth.grantType"), StandardCharsets.UTF_8),
                    URLEncoder.encode(config.getConfigInc().getString("auth.clientID"),StandardCharsets.UTF_8),
                    URLEncoder.encode(config.getConfigInc().getString("auth.clientSecret"),StandardCharsets.UTF_8),
                    URLEncoder.encode(config.getConfigInc().getString("auth.scope"), StandardCharsets.UTF_8));
            this.logger.info("The request content:[{}]", requestContent);
            Map<String,String> header  = new HashMap<>();
            header.put("Content-Type", "application/x-www-form-urlencoded");

            WS000001Request ws000001Request = new WS000001Request();
            ws000001Request.setContent(requestContent);
            ResponseMeta<WS000001Response> meshResponseMeta = this.downstreamWS000001.callWS000001(header, ws000001Request);
            WS000001Response ws000001Response = meshResponseMeta.response();

            if (!StringUtils.isAllEmpty(ws000001Response.getError())) {
                throw new ServiceException(SYSTEM_INTERNAL_ERROR, ws000001Response.getError());
            }

            return ws000001Response.getAccessToken();

        } catch (Exception e) {
            this.logger.error("Get token failed, err:" + e.getMessage());
            throw e;
        }
    }

    private WS000002Response accountBalanceAccumulation(String transactionID, String accountID, long amount, String token) {
        Map<String,String> header  = new HashMap<>();
        header.put("transactionId", transactionID);
        header.put("Content-Type", "application/json");
        header.put("Authorization", String.format("Bearer %s", token));
        WS000002Request ws000002Request = new WS000002Request();
        ws000002Request.setAccountId(accountID);
        ws000002Request.setAmount(amount);
        ResponseMeta<WS000002Response> meshRequestMeta = this.downstreamWS000002.callWS000002(header, ws000002Request);

        return meshRequestMeta.response();
    }

    private OB9M1001Response callSob9m01001(String userID) {
        OB9M1001Request ob9M1001Request = new OB9M1001Request();
        ob9M1001Request.setIdUser(userID);
        ResponseMeta<OB9M1001Response> responseMeta = this.downstreamOB9M1001.callOB9M1001(null, ob9M1001Request);

        return responseMeta.response();
    }

    private OB1M1001Response callSob1m01001(long amount) {
        OB1M1001Request ob1M1001Request = new OB1M1001Request();
        ob1M1001Request.setAmount(amount);
        ResponseMeta<OB1M1001Response> responseMeta = this.downstreamOB1M1001.callOB1M1001(null, ob1M1001Request);

        return responseMeta.response();
    }
}
