package com.sirius.io.sob9m01002.service;

import com.alibaba.fastjson.JSON;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.common.tuples.Tuple2;
import com.multiverse.eventkit.kit.db.util.Util;
import com.sirius.io.constant.ErrorCode;
import com.sirius.io.dao.PageResult;
import com.sirius.io.model.OB9M1002Request;
import com.sirius.io.model.OB9M1002Response;
import com.sirius.io.sob9m01002.dao.BalanceAccumulationHistoryDAO;
import com.sirius.io.sob9m01002.entity.BalanceAccumulationHistory;
import com.sirius.io.sob9m01002.handler.Sob9m01002Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import static com.multiverse.eventkit.common.constant.ErrorCode.SYSTEM_INTERNAL_ERROR;

@Component
public class ServiceOB9M1002 {
    private final Logger logger = LoggerFactory.getLogger(Sob9m01002Handler.class);

    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private BalanceAccumulationHistoryDAO balanceAccumulationHistoryDAO;

    @Autowired
    private AmazonS3 amazonS3;


    public Tuple2<OB9M1002Response, Map<String,String>> handleOB9M1002(Map<String,String> requestHeader, OB9M1002Request ob9M1002Request) throws ServiceException {
        BalanceAccumulationHistory transactionHistory = new BalanceAccumulationHistory();
        transactionHistory.setTransactionId(ob9M1002Request.getTransactionId());
        transactionHistory.setIdUser(ob9M1002Request.getIdUser());
        transactionHistory.setIdAccount(ob9M1002Request.getIdAccount());
        transactionHistory.setTransactionAmount(ob9M1002Request.getTransactionAmount());
        transactionHistory.setRetCode(ob9M1002Request.getRetCode());
        transactionHistory.setRetMessage(ob9M1002Request.getRetMessage());
        transactionHistory.setBalance(ob9M1002Request.getBalance());
        transactionHistory.setCreateTime(new Timestamp(new Date().getTime()));

        if (0 == this.balanceAccumulationHistoryDAO.insert(transactionHistory)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to insert record into db");
        }

        PutObjectResult putObjectResult;
        // 2. save the transaction information into s3
        if (config.getConfigInc().getBoolean("s3.enable")) {
            try {
                putObjectResult = amazonS3.putObject(config.getConfigInc().getString("s3.bucket"), transactionHistory.getTransactionId(), JSON.toJSONString(transactionHistory));
                logger.info("Successfully put object to S3, the putObjectResult:[ETag: {} - VersionId: {} - Expiration: {} - contentMd5: {}]",
                        putObjectResult.getETag(), putObjectResult.getVersionId(), putObjectResult.getExpirationTime(), putObjectResult.getContentMd5());
            } catch (Exception e) {
                this.logger.error("Failed to put object to s3 ", e);
                throw new ServiceException(SYSTEM_INTERNAL_ERROR, String.format("Failed to put object to s3: %s", e.getMessage()));
            }
        }

        OB9M1002Response OB9M1002Response = new OB9M1002Response();
        OB9M1002Response.setCreateDate(transactionHistory.getCreateTime().toString());

        return new Tuple2<>(OB9M1002Response, null);
    }

    public Tuple2<OB9M1002Response, Map<String,String>> findById(Map<String,String> requestHeader, OB9M1002Request ob9M1002Request) {
        BalanceAccumulationHistory balanceAccumulationHistory = new BalanceAccumulationHistory();
        balanceAccumulationHistory.setTransactionId(ob9M1002Request.getTransactionId());

        BalanceAccumulationHistory retrievedObject = this.balanceAccumulationHistoryDAO.findById(balanceAccumulationHistory);

        if (null == retrievedObject) {
            throw new ServiceException(ErrorCode.Banc000001, "Cannot found record by user id:%s", ob9M1002Request.getIdUser());
        }
        OB9M1002Response OB9M1002Response = new OB9M1002Response();

        OB9M1002Response.setTransactionId(retrievedObject.getTransactionId());
        OB9M1002Response.setIdUser(retrievedObject.getIdUser());
        OB9M1002Response.setBalance(retrievedObject.getBalance());
        OB9M1002Response.setRetCode(retrievedObject.getRetCode());
        OB9M1002Response.setRetMessage(retrievedObject.getRetMessage());
        OB9M1002Response.setTransactionAmount(retrievedObject.getTransactionAmount());
        OB9M1002Response.setCreateDate(retrievedObject.getCreateTime().toString());

        return new Tuple2<>(OB9M1002Response, null);
    }

    public Tuple2<OB9M1002Response, Map<String,String>> update(Map<String,String> requestHeader, OB9M1002Request ob9M1002Request) {
        BalanceAccumulationHistory transactionHistory = new BalanceAccumulationHistory();
        transactionHistory.setTransactionId(ob9M1002Request.getTransactionId());

        if (0 == this.balanceAccumulationHistoryDAO.updateById(transactionHistory)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to update the record");
        }

        return new Tuple2<>(new OB9M1002Response(), null);
    }

    public Tuple2<OB9M1002Response, Map<String,String>> delete(Map<String,String> requestHeader, OB9M1002Request ob9M1002Request) {
        BalanceAccumulationHistory balanceAccumulationHistory = new BalanceAccumulationHistory();
        balanceAccumulationHistory.setTransactionId(ob9M1002Request.getTransactionId());

        if (0 == this.balanceAccumulationHistoryDAO.deleteById(balanceAccumulationHistory)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to delete record by user id:%s", ob9M1002Request.getIdUser());
        }

        return new Tuple2<>(new OB9M1002Response(), null);
    }

    public PageResult<BalanceAccumulationHistory> findPage(OB9M1002Request ob9M1002Request) throws ServiceException {
        return this.balanceAccumulationHistoryDAO.findPage(Util.currentDbType(), ob9M1002Request.getPage(), ob9M1002Request.getPageSize(), "", "", null);
    }
}
