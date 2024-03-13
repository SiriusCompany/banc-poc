package com.sirius.io.sob0m01000.downstream;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.kit.client.ResponseMeta;
import com.multiverse.eventkit.kit.client.mesh.DefaultRequest;
import com.multiverse.eventkit.kit.handler.call.RemoteCall;
import com.sirius.io.model.AOGResponse;
import com.sirius.io.model.WS000002Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DownstreamWS000002 {
    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private RemoteCall remoteCall;

    public ResponseMeta<AOGResponse> callWS000002(Map<String, String> header, WS000002Request ws000002Request) {
        DefaultRequest meshRequest = DefaultRequest.build()
                .setBody(ws000002Request);

        if (null != header) {
            meshRequest.requestOptions().setHeader(header);
        }

        ResponseMeta<AOGResponse> meshRequestMeta = this.remoteCall.syncCall(config.getService().getCommonSu(),
                "WS000002", meshRequest, AOGResponse.class);

        return meshRequestMeta;
    }
}
