package com.sirius.io.sob9m01001.service;

import com.multiverse.eventkit.common.config.ServiceConfigsProperties;
import com.multiverse.eventkit.common.exception.ServiceException;
import com.multiverse.eventkit.common.tuples.Tuple2;
import com.multiverse.eventkit.kit.db.util.Util;
import com.sirius.io.constant.ErrorCode;
import com.sirius.io.dao.PageResult;
import com.sirius.io.model.OB9M1001Request;
import com.sirius.io.model.OB9M1001Response;
import com.sirius.io.sob9m01001.dao.UserAccountMappingDAO;
import com.sirius.io.sob9m01001.entity.UserAccountMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceOB9M1001 {
    private final Logger logger = LoggerFactory.getLogger(ServiceOB9M1001.class);

    @Autowired
    private ServiceConfigsProperties config;

    @Autowired
    private UserAccountMappingDAO userAccountMappingDAO;


    public Tuple2<OB9M1001Response, Map<String,String>>  handleOB9M1001(OB9M1001Request ob9M1001Request) throws ServiceException {
        UserAccountMapping userAccountMapping = new UserAccountMapping();
        userAccountMapping.setIdUser(ob9M1001Request.getIdUser());
        UserAccountMapping retrievedObject = this.userAccountMappingDAO.findById(userAccountMapping);

        if (null == retrievedObject) {
            throw new ServiceException(ErrorCode.Banc000001, "Cannot found record by user id:%s", ob9M1001Request.getIdUser());
        }
        OB9M1001Response OB9M1001Response = new OB9M1001Response();
        OB9M1001Response.setIdAccount(retrievedObject.getIdAccount());
        return new Tuple2<>(OB9M1001Response, null);
    }

    public Tuple2<OB9M1001Response, Map<String,String>> insert(OB9M1001Request ob9M1001Request) throws ServiceException {
        UserAccountMapping userAccountMapping = new UserAccountMapping();
        userAccountMapping.setIdUser(ob9M1001Request.getIdUser());
        // TODO: Need to modify manually
//        userAccountMapping.setIdAccount(ob9M1001Request.getIdAccount());
        if (0 == this.userAccountMappingDAO.insert(userAccountMapping)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to insert record into db");
        }

        return new Tuple2<>(new OB9M1001Response(), null);
    }

    public Tuple2<OB9M1001Response, Map<String,String>> update(OB9M1001Request ob9M1001Request) throws ServiceException {
        UserAccountMapping userAccountMapping = new UserAccountMapping();
        userAccountMapping.setIdUser(ob9M1001Request.getIdUser());
        // TODO: Need to modify manually
//        userAccountMapping.setIdAccount(ob9M1001Request.getIdAccount());
        if (0 == this.userAccountMappingDAO.updateById(userAccountMapping)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to update the record");
        }

        return new Tuple2<>(new OB9M1001Response(), null);
    }

    public Tuple2<OB9M1001Response, Map<String,String>> delete(OB9M1001Request ob9M1001Request) throws ServiceException {
        UserAccountMapping userAccountMapping = new UserAccountMapping();
        userAccountMapping.setIdUser(ob9M1001Request.getIdUser());
        if (0 == this.userAccountMappingDAO.deleteById(userAccountMapping)) {
            throw new ServiceException(ErrorCode.Banc000001, "Failed to delete record by user id:%s", ob9M1001Request.getIdUser());
        }

        return new Tuple2<>(new OB9M1001Response(), null);
    }


    public PageResult<UserAccountMapping> findPage(OB9M1001Request ob9M1001Request) throws ServiceException {
        return this.userAccountMappingDAO.findPage(Util.currentDbType(), ob9M1001Request.getPage(), ob9M1001Request.getPageSize(), "", "", null);
    }
}
