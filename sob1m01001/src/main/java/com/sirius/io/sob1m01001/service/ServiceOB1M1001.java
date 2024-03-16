package com.sirius.io.sob1m01001.service;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.common.tuples.Tuple2;
import com.sirius.io.model.OB1M1001Request;
import com.sirius.io.model.OB1M1001Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceOB1M1001 {

    @Autowired
    private ServiceConfigsProperties config;

    public Tuple2<OB1M1001Response, Map<String,String>> handleOB1M1001(Map<String,String> requestHeader, OB1M1001Request ob1M1001Request) throws ServiceException {
        OB1M1001Response ob1M1001Response = new OB1M1001Response();
        ob1M1001Response.setOk(true);

        long limit = this.config.getConfigInc().getLong("transaction.limit", -1);
        if (-1 != limit && ob1M1001Request.getAmount() > limit) {
            ob1M1001Response.setOk(false);
        } else {
            ob1M1001Response.setOk(true);
        }

        return new Tuple2<>(ob1M1001Response, null);
    }
}
