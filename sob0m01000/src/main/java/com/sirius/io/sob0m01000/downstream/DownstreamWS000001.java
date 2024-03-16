package com.sirius.io.sob0m01000.downstream;

import com.alibaba.fastjson.JSON;
import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.kit.client.ResponseMeta;
import com.multiverse.eventkit.kit.client.mesh.DefaultRequest;
import com.multiverse.eventkit.kit.client.mesh.DefaultResponseMeta;
import com.multiverse.eventkit.kit.enums.CodecType;
import com.multiverse.eventkit.kit.handler.call.RemoteCall;
import com.sirius.io.model.AOGResponse;
import com.sirius.io.model.WS000001Request;
import com.sirius.io.model.WS000001Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Component
public class DownstreamWS000001 {
    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private RemoteCall remoteCall;

    public ResponseMeta<WS000001Response> callWS000001(Map<String,String> header, WS000001Request ws000001Request) {
        DefaultRequest meshRequest = DefaultRequest.build().setBody(
                null == ws000001Request.getContent() ? null: ws000001Request.getContent().getBytes(StandardCharsets.UTF_8)
        );
        if (null != header) {
            meshRequest.requestOptions().setHeader(header);
        }

        meshRequest.requestOptions().setHeader(header)
                .setEncoder(CodecType.TEXT)
                .setDecoder(CodecType.JSON);
        ResponseMeta<AOGResponse> responseMeta = this.remoteCall.syncCall(config.getService().getCommonSu(),
                "WS000001", meshRequest, AOGResponse.class);

        byte[] decodedBytes = Base64.getDecoder().decode(responseMeta.response().getBody());

        WS000001Response ws000001Response = JSON.parseObject(decodedBytes, WS000001Response.class);

        return DefaultResponseMeta.build()
                .setResponse(ws000001Response)
                .setBody(responseMeta.originBody())
                .setHeader(responseMeta.header());
    }
}
