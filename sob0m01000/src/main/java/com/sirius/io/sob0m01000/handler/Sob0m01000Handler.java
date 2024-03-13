package com.sirius.io.sob0m01000.handler;

import com.multiverse.eventkit.common.tuples.Tuple2;
import com.multiverse.eventkit.kit.contexts.HandlerContext;
import com.sirius.io.model.*;
import com.sirius.io.sob0m01000.service.ServiceOB0M1000;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.kit.annotation.EventHandler;
import com.multiverse.eventkit.kit.annotation.Router;
import com.multiverse.eventkit.kit.handler.BaseHandler;

import java.util.*;

@EventHandler
public class Sob0m01000Handler extends BaseHandler {

    private final Logger logger = LoggerFactory.getLogger(Sob0m01000Handler.class);

    @Autowired
    private ServiceOB0M1000 serviceOB0M1000;


    @Router(eventKey = "OB0M1000", handlePost = "/v1/sob0m01000")
    public OB0M1000Response handleOB0M1000(OB0M1000Request ob0M1000Request) throws ServiceException {
        this.logger.info("start exec sob0m01000 handler, trace ID:{}", HandlerContext.getContext().getSpanContext().getTraceId());
        Tuple2<OB0M1000Response, Map<String,String>> tuple2 = this.serviceOB0M1000.handleOB0M1000(this.getRequestHeader(), ob0M1000Request);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }
}
