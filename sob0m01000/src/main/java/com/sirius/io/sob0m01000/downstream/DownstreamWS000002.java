package com.sirius.io.sob0m01000.downstream;

import com.alibaba.fastjson.JSON;
import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.kit.client.ResponseMeta;
import com.multiverse.eventkit.kit.client.mesh.DefaultRequest;
import com.multiverse.eventkit.kit.client.mesh.DefaultResponseMeta;
import com.multiverse.eventkit.kit.handler.call.RemoteCall;
import com.sirius.io.model.AOGResponse;
import com.sirius.io.model.WS000002Request;
import com.sirius.io.model.WS000002Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

import static com.multiverse.eventkit.common.constant.ErrorCode.SYSTEM_INTERNAL_ERROR;

@Component
public class DownstreamWS000002 {
    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private RemoteCall remoteCall;

    public ResponseMeta<WS000002Response> callWS000002(Map<String,String> header, WS000002Request ws000002Request) {
        DefaultRequest meshRequest = DefaultRequest.build().setBody(ws000002Request);
        if (null != header) {
            meshRequest.requestOptions().setHeader(header);
        }

        ResponseMeta<AOGResponse> responseMeta = this.remoteCall.syncCall(config.getService().getCommonSu(),
                "WS000002", meshRequest, AOGResponse.class);

        WS000002Response ws000002Response;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(responseMeta.response().getBody());
            ws000002Response = JSON.parseObject(decodedBytes, WS000002Response.class);
            if (!StringUtils.isAllEmpty(ws000002Response.getError())) {
                throw new ServiceException(SYSTEM_INTERNAL_ERROR, ws000002Response.getError());
            }
        } catch (ServiceException se) {
            throw se;
        }
        catch (Exception e) {
            throw new ServiceException(SYSTEM_INTERNAL_ERROR, String.format("Failed to request the service: https://iolc7ic3g2.execute-api.us-east-1.amazonaws.com/dev/micro-accounts/api/consignar, the response is: %s", responseMeta.response().getBody()));
        }

        return DefaultResponseMeta.build()
                .setResponse(ws000002Response)
                .setBody(responseMeta.originBody())
                .setHeader(responseMeta.header());
    }
}
