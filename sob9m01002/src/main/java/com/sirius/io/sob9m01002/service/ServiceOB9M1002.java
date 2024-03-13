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
import com.sirius.io.sob9m01002.dao.TransactionHistoryDAO;
import com.sirius.io.sob9m01002.entity.TransactionHistory;
import com.sirius.io.sob9m01002.handler.Sob9m01002Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

import static com.multiverse.eventkit.common.constant.ErrorCode.SYSTEM_INTERNAL_ERROR;

@Component
public class ServiceOB9M1002 {
    private final Logger logger = LoggerFactory.getLogger(Sob9m01002Handler.class);

    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private TransactionHistoryDAO transactionHistoryDAO;

    @Autowired
    private AmazonS3 amazonS3;


    public Tuple2<OB9M1002Response, Map<String,String>> handleOB9M1002(Map<String,String> requestHeader, OB9M1002Request ob9M1002Request) throws ServiceException {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionID(ob9M1002Request.getTransactionID());
        transactionHistory.setUserID(ob9M1002Request.getUserID());
        transactionHistory.setAccountID(ob9M1002Request.getAccountID());
        transactionHistory.setTransactionAmount(ob9M1002Request.getTransactionAmount());
        transactionHistory.setRetCode(ob9M1002Request.getRetCode());
        transactionHistory.setRetMessage(ob9M1002Request.getRetMessage());
        transactionHistory.setBalance(ob9M1002Request.getBalance());
        transactionHistory.setCreateTime(new Date());

        if (0 == transactionHistoryDAO.insert(transactionHistory)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to insert record into db");
        }

        PutObjectResult putObjectResult;
        // 2. save the transaction information into s3
        if (config.getConfigInc().getBoolean("s3.enable")) {
            try {
                putObjectResult = amazonS3.putObject(config.getConfigInc().getString("s3.bucket"), transactionHistory.getTransactionID(), JSON.toJSONString(transactionHistory));
                logger.info("Successfully put object to S3, the putObjectResult:[ETag: {} - VersionId: {} - Expiration: {} - contentMd5: {}]",
                        putObjectResult.getETag(), putObjectResult.getVersionId(), putObjectResult.getExpirationTime(), putObjectResult.getContentMd5());
            } catch (Exception e) {
                this.logger.error("Failed to put object to s3 ", e);
                throw new ServiceException(SYSTEM_INTERNAL_ERROR, String.format("Failed to put object to s3: %s", e.getMessage()));
            }
        }

        OB9M1002Response OB9M1002Response = new OB9M1002Response();
        OB9M1002Response.setCreateTime(transactionHistory.getCreateTime().toString());

        return new Tuple2<>(OB9M1002Response, null);
    }

    public Tuple2<OB9M1002Response, Map<String,String>> findById(Map<String,String> requestHeader, OB9M1002Request ob9M1002Request) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionID(ob9M1002Request.getTransactionID());

        TransactionHistory retrievedObject = this.transactionHistoryDAO.findById(transactionHistory);

        if (null == retrievedObject) {
            throw new ServiceException(ErrorCode.Banc000001, "Cannot found record by user id:%s", ob9M1002Request.getUserID());
        }
        OB9M1002Response OB9M1002Response = new OB9M1002Response();

        OB9M1002Response.setTransactionID(retrievedObject.getTransactionID());
        OB9M1002Response.setUserID(retrievedObject.getUserID());
        OB9M1002Response.setBalance(retrievedObject.getBalance());
        OB9M1002Response.setRetCode(retrievedObject.getRetCode());
        OB9M1002Response.setRetMessage(retrievedObject.getRetMessage());
        OB9M1002Response.setTransactionAmount(retrievedObject.getTransactionAmount());
        OB9M1002Response.setCreateTime(retrievedObject.getCreateTime().toString());

        return new Tuple2<>(OB9M1002Response, null);
    }

    public Tuple2<OB9M1002Response, Map<String,String>> update(Map<String,String> requestHeader, OB9M1002Request ob9M1002Request) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionID(ob9M1002Request.getTransactionID());

        if (0 == this.transactionHistoryDAO.updateById(transactionHistory)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to update the record");
        }

        return new Tuple2<>(new OB9M1002Response(), null);
    }

    public Tuple2<OB9M1002Response, Map<String,String>> delete(Map<String,String> requestHeader, OB9M1002Request ob9M1002Request) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionID(ob9M1002Request.getTransactionID());

        if (0 == this.transactionHistoryDAO.deleteById(transactionHistory)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to delete record by user id:%s", ob9M1002Request.getUserID());
        }

        return new Tuple2<>(new OB9M1002Response(), null);
    }

    public PageResult<TransactionHistory> findPage(OB9M1002Request ob9M1002Request) throws ServiceException {
        return this.transactionHistoryDAO.findPage(Util.currentDbType(), ob9M1002Request.getPage(), ob9M1002Request.getPageSize(), "", "", null);
    }
}
