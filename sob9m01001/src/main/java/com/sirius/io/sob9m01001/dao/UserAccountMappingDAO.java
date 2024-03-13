package com.sirius.io.sob9m01001.dao;

import com.sirius.io.dao.GenericDao;
import com.sirius.io.sob9m01001.entity.UserAccountMapping;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountMappingDAO extends GenericDao<UserAccountMapping> {

    public UserAccountMappingDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate, new BeanPropertyRowMapper<>(UserAccountMapping.class));
    }

    @Override
    public MapSqlParameterSource getParameterSource(UserAccountMapping entity) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        if (null != entity) {
            paramMap.addValue("idUser", entity.getIdUser());
            paramMap.addValue("idAccount", entity.getIdAccount());
        }
        return paramMap;
    }

    @Override
    protected String getInsertColumns() {
        return ("id_user,id_account");
    }

    @Override
    protected String getInsertValues() {
        return (":idUser,:idAccount");
    }

    @Override
    protected String getUpdateColumns() {
        return ("id_account = :idAccount");
    }

    @Override
    protected String getTableName() {
        return "user_account_mapping";
    }

    @Override
    protected String getPrimaryKeyCondition() {
        return "id_user = :idUser";
    }
}