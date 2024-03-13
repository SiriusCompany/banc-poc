package com.sirius.io.sob1m01001.handler;

import com.multiverse.eventkit.common.tuples.Tuple2;
import com.sirius.io.model.OB1M1001Request;
import com.sirius.io.model.OB1M1001Response;
import com.sirius.io.sob1m01001.service.ServiceOB1M1001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.kit.annotation.EventHandler;
import com.multiverse.eventkit.kit.annotation.Router;
import com.multiverse.eventkit.kit.handler.BaseHandler;

import java.util.Map;

@EventHandler
public class Sob1m01001Handler extends BaseHandler {

    private final Logger logger = LoggerFactory.getLogger(Sob1m01001Handler.class);

    @Autowired
    private ServiceOB1M1001 serviceOB1M1001;

    @Router(eventKey = "OB1M1001", handlePost = "/v1/sob1m01001")
    public OB1M1001Response sob1m01001(OB1M1001Request ob1M1001Request) throws ServiceException {
        Tuple2<OB1M1001Response, Map<String,String>> tuple2 = serviceOB1M1001.handleOB1M1001(this.getRequestHeader(), ob1M1001Request);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }
}
