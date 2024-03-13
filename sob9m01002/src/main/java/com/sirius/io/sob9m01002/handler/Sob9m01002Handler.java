package com.sirius.io.sob9m01002.handler;

import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.common.tuples.Tuple2;
import com.multiverse.eventkit.kit.annotation.EventHandler;
import com.multiverse.eventkit.kit.annotation.Router;
import com.multiverse.eventkit.kit.handler.BaseHandler;
import com.sirius.io.dao.PageResult;
import com.sirius.io.model.OB9M1002Request;
import com.sirius.io.model.OB9M1002Response;
import com.sirius.io.sob9m01002.entity.TransactionHistory;
import com.sirius.io.sob9m01002.service.ServiceOB9M1002;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@EventHandler
public class Sob9m01002Handler extends BaseHandler {
    private final Logger logger = LoggerFactory.getLogger(Sob9m01002Handler.class);

    @Autowired
    private ServiceOB9M1002 serviceOB9M1002;


//    @Router(eventKey = "XXXXXX", handlePost = "/v1/findById")
    public OB9M1002Response findById(OB9M1002Request ob9M1002Request) throws ServiceException {
        Tuple2<OB9M1002Response, Map<String,String>> tuple2 = serviceOB9M1002.findById(this.getRequestHeader(), ob9M1002Request);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }

    @Router(eventKey = "OB9M1002", handlePost = "/v1/insert")
    public OB9M1002Response handleOB9M1002(OB9M1002Request ob9M1002Request) throws ServiceException {
        Tuple2<OB9M1002Response, Map<String,String>> tuple2 = serviceOB9M1002.handleOB9M1002(this.getRequestHeader(), ob9M1002Request);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }

    //    @Router(eventKey = "XXXXXX", handlePost = "/v1/update")
    public OB9M1002Response update(OB9M1002Request ob9M1002Request) throws ServiceException {
        Tuple2<OB9M1002Response, Map<String,String>> tuple2 = serviceOB9M1002.update(this.getRequestHeader(), ob9M1002Request);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }

    //    @Router(eventKey = "XXXXXX", handlePost = "/v1/delete")
    public OB9M1002Response delete(OB9M1002Request ob9M1002Request) throws ServiceException {
        Tuple2<OB9M1002Response, Map<String,String>> tuple2 = serviceOB9M1002.delete(this.getRequestHeader(), ob9M1002Request);

        if (null != tuple2.snd) {
            this.getResponseHeader().putAll(tuple2.snd);
        }

        return tuple2.fst;
    }

//    @Router(eventKey = "XXXXXX", handlePost = "/v1/findPage")
    public PageResult<TransactionHistory> findPage(OB9M1002Request ob9M1002Request) throws ServiceException {
        return serviceOB9M1002.findPage(ob9M1002Request);
    }

}
