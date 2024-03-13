package com.sirius.io.sob0m01000.downstream;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.kit.client.ResponseMeta;
import com.multiverse.eventkit.kit.client.mesh.DefaultRequest;
import com.multiverse.eventkit.kit.handler.call.RemoteCall;
import com.sirius.io.model.OB9M1001Request;
import com.sirius.io.model.OB9M1001Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DownstreamOB9M1001 {
    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private RemoteCall remoteCall;

    public ResponseMeta<OB9M1001Response> callOB9M1001(Map<String,String> header, OB9M1001Request ob9M1001Request) {
        DefaultRequest meshRequest = DefaultRequest.build().setBody(ob9M1001Request);
        if (null != header) {
            meshRequest.requestOptions().setHeader(header);
        }

        ResponseMeta<OB9M1001Response> responseMeta = this.remoteCall.syncCall(config.getService().getCommonSu(),
                "OB9M1001", meshRequest, OB9M1001Response.class);

        return responseMeta;
    }
}
