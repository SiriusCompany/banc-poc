package com.sirius.io.publisher.service;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.common.tuples.Tuple2;
import com.sirius.io.model.*;
import com.sirius.io.publisher.downstream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class ServicePublisher {
    private final Logger logger = LoggerFactory.getLogger(ServicePublisher.class);

    @Autowired
    private ServiceConfigsProperties config;
    @Autowired
    private DownstreamConsumer downstreamConsumer;


    public Tuple2<PublisherResponse, Map<String,String>> handlePublisher(Map<String,String> requestHeader, PublisherRequest publisherRequest) throws ServiceException {
        ConsumerRequest consumerRequest = new ConsumerRequest();

        consumerRequest.setData(publisherRequest.getData());
        this.downstreamConsumer.callConsumer(null, consumerRequest);

        PublisherResponse publisherResponse = new PublisherResponse();
        publisherResponse.setData("Response from Publisher service");
        return new Tuple2<>(publisherResponse, null);
    }
}
