package com.sirius.io.sob9m01001.handler;

import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.common.tuples.Tuple2;
import com.multiverse.eventkit.kit.annotation.EventHandler;
import com.multiverse.eventkit.kit.annotation.Router;
import com.multiverse.eventkit.kit.handler.BaseHandler;
import com.sirius.io.dao.PageResult;
import com.sirius.io.model.OB9M1001Request;
import com.sirius.io.model.OB9M1001Response;
import com.sirius.io.sob9m01001.entity.UserAccountMapping;
import com.sirius.io.sob9m01001.service.ServiceOB9M1001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


@EventHandler
public class Sob9m01001Handler extends BaseHandler {
    private final Logger logger = LoggerFactory.getLogger(Sob9m01001Handler.class);

    @Autowired
    private ServiceOB9M1001 serviceOB9M1001;


    @Router(eventKey = "OB9M1001", handlePost = "/v1/findById")
    public OB9M1001Response handleOB9M1001(OB9M1001Request ob9M1001Request) throws ServiceException {
        Tuple2<OB9M1001Response, Map<String,String>> tuple2 = serviceOB9M1001.handleOB9M1001(ob9M1001Request);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }

//    @Router(eventKey = "XXXXXX", handlePost = "/v1/insert")
    public OB9M1001Response insert(OB9M1001Request ob9M1001Request) throws ServiceException {
        Tuple2<OB9M1001Response, Map<String,String>> tuple2 = serviceOB9M1001.insert(ob9M1001Request);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }

//    @Router(eventKey = "XXXXXX", handlePost = "/v1/update")
    public OB9M1001Response update(OB9M1001Request ob9M1001Request) throws ServiceException {
        Tuple2<OB9M1001Response, Map<String,String>> tuple2 = serviceOB9M1001.update(ob9M1001Request);
        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }

//    @Router(eventKey = "XXXXXX", handlePost = "/v1/delete")
    public OB9M1001Response delete(OB9M1001Request ob9M1001Request) throws ServiceException {
        Tuple2<OB9M1001Response, Map<String,String>> tuple2 = serviceOB9M1001.delete(ob9M1001Request);
        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }


//    @Router(eventKey = "XXXXXX", handlePost = "/v1/findPage")
    public PageResult<UserAccountMapping> findPage(OB9M1001Request ob9M1001Request) throws ServiceException {
        return serviceOB9M1001.findPage(ob9M1001Request);
    }

}
