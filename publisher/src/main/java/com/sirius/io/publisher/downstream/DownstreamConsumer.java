package com.sirius.io.publisher.downstream;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.kit.client.mesh.DefaultRequest;
import com.multiverse.eventkit.kit.handler.call.RemoteCall;
import com.sirius.io.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DownstreamConsumer {
    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private RemoteCall remoteCall;

    public void callConsumer(Map<String,String> header, ConsumerRequest consumerRequest) {
        DefaultRequest meshRequest = DefaultRequest.build().setBody(consumerRequest);
        if (null != header) {
            meshRequest.requestOptions().setHeader(header);
        }

        this.remoteCall.asyncCall(config.getService().getCommonSu(), "consumer", meshRequest);
    }
}
