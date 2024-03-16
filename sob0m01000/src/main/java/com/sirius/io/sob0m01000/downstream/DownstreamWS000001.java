package com.sirius.io.sob0m01000.downstream;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.kit.client.ResponseMeta;
import com.multiverse.eventkit.kit.client.mesh.DefaultRequest;
import com.multiverse.eventkit.kit.enums.CodecType;
import com.multiverse.eventkit.kit.handler.call.RemoteCall;
import com.sirius.io.model.AOGResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class DownstreamWS000001 {
    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private RemoteCall remoteCall;

    public ResponseMeta<AOGResponse> callWS000001(Map<String,String> header, String ws000001Request) {
        DefaultRequest meshRequest = DefaultRequest.build()
                .setBody(ws000001Request.getBytes(StandardCharsets.UTF_8));

        meshRequest.requestOptions().setHeader(header)
                .setEncoder(CodecType.TEXT)
                .setDecoder(CodecType.JSON);
        ResponseMeta<AOGResponse> meshResponseMeta = this.remoteCall.syncCall(config.getService().getCommonSu(),
                "WS000001", meshRequest, AOGResponse.class);

        return meshResponseMeta;
    }
}
