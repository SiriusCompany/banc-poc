package com.sirius.io.consumer.service;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.common.exception.ServiceException;
import com.sirius.io.consumer.handler.ConsumerHandler;
import com.sirius.io.model.ConsumerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceConsumer {

    private final Logger logger = LoggerFactory.getLogger(ConsumerHandler.class);

    @Autowired
    private ServiceConfigsProperties config;

    public void handleConsumer(Map<String,String> requestHeader, ConsumerRequest consumerRequest) throws ServiceException {
        logger.info("The request of consumer:{}", consumerRequest);
    }
}
