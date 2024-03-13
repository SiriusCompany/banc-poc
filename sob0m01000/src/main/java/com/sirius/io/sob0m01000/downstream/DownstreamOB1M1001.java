package com.sirius.io.sob0m01000.downstream;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.kit.client.ResponseMeta;
import com.multiverse.eventkit.kit.client.mesh.DefaultRequest;
import com.multiverse.eventkit.kit.handler.call.RemoteCall;
import com.sirius.io.model.OB1M1001Request;
import com.sirius.io.model.OB1M1001Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DownstreamOB1M1001 {
    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private RemoteCall remoteCall;

    public ResponseMeta<OB1M1001Response> callOB1M1001(Map<String,String> header, OB1M1001Request ob1M1001Request) {
        DefaultRequest meshRequest = DefaultRequest.build().setBody(ob1M1001Request);
        if (null != header) {
            meshRequest.requestOptions().setHeader(header);
        }

        ResponseMeta<OB1M1001Response> responseMeta = this.remoteCall.syncCall(config.getService().getCommonSu(),
                "OB1M1001", meshRequest, OB1M1001Response.class);

        return responseMeta;
    }
}
