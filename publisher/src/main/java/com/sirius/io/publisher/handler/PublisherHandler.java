package com.sirius.io.publisher.handler;

import com.multiverse.eventkit.common.tuples.Tuple2;
import com.multiverse.eventkit.kit.contexts.HandlerContext;
import com.sirius.io.model.*;
import com.sirius.io.publisher.service.ServicePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.kit.annotation.EventHandler;
import com.multiverse.eventkit.kit.annotation.Router;
import com.multiverse.eventkit.kit.handler.BaseHandler;

import java.util.*;

@EventHandler
public class PublisherHandler extends BaseHandler {

    private final Logger logger = LoggerFactory.getLogger(PublisherHandler.class);

    @Autowired
    private ServicePublisher servicePublisher;


    @Router(eventKey = "publisher", handlePost = "/v1/publisher")
    public PublisherResponse handlePublisher(PublisherRequest publisherRequest) throws ServiceException {
        this.logger.info("start exec publisher handler, trace ID:{}", HandlerContext.getContext().getSpanContext().getTraceId());
        Tuple2<PublisherResponse, Map<String,String>> tuple2 = this.servicePublisher.handlePublisher(this.getRequestHeader(), publisherRequest);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }
}
