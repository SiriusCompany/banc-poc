package com.sirius.io.consumer.handler;

import com.sirius.io.model.ConsumerRequest;
import com.sirius.io.consumer.service.ServiceConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.kit.annotation.EventHandler;
import com.multiverse.eventkit.kit.annotation.Router;
import com.multiverse.eventkit.kit.handler.BaseHandler;

@EventHandler
public class ConsumerHandler extends BaseHandler {
    private final Logger logger = LoggerFactory.getLogger(ConsumerHandler.class);

    @Autowired
    private ServiceConsumer serviceConsumer;

    @Router(eventKey = "consumer", handlePost = "/v1/consumer")
    public void consumer(ConsumerRequest consumerRequest) throws ServiceException {
        serviceConsumer.handleConsumer(this.getRequestHeader(), consumerRequest);
    }
}
