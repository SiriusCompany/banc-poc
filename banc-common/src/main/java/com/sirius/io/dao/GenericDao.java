package com.sirius.io.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDao<T extends Serializable> {
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    protected RowMapper<T> rowMapper;


    public abstract MapSqlParameterSource getParameterSource(T entity);

    public GenericDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RowMapper<T> rowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public int insert(T entity) {
        String sql = "INSERT INTO " + getTableName() + " ( " + getInsertColumns() + " ) VALUES ( " + getInsertValues() + " )";
        SqlParameterSource paramMap = getParameterSource(entity);
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }

    public int updateById(T entity) {
        String sql = "UPDATE " + getTableName() + " SET " + getUpdateColumns() + " WHERE " + getPrimaryKeyCondition();
        SqlParameterSource paramMap = getParameterSource(entity);
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }

    public int deleteById(T entity) {
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyCondition();
        SqlParameterSource paramMap = getParameterSource(entity);
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }

    public T findById(T entity) {
        try {
            String sql = "SELECT * FROM " + getTableName() + " WHERE " + getPrimaryKeyCondition();
            SqlParameterSource paramMap = getParameterSource(entity);
            return namedParameterJdbcTemplate.queryForObject(sql, paramMap, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<T> findAll(String orderBy) {
        try {
            String sql = "SELECT * FROM " + getTableName() + " ORDER BY " + orderBy;
            return namedParameterJdbcTemplate.query(sql, rowMapper);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public PageResult<T> findPage(String dbType, int page, int pageSize, String condition, String orderBy, MapSqlParameterSource paramMap) {
        if (null == paramMap) {
            paramMap = new MapSqlParameterSource();
        }
        int offset = (page - 1) * pageSize;
        String sqlData;
        String sqlCount = "SELECT COUNT(1) FROM " + getTableName();
        if (null == dbType) {
            return null;
        }
        switch (dbType) {
            case "oracle": {
                sqlData = "SELECT * FROM (SELECT t.*, ROWNUM AS rn FROM " + getTableName() + " t";
                if (null != condition && !"".equals(condition)) {
                    sqlData += " WHERE " + condition + " AND ";
                    sqlCount += " WHERE " + condition;
                }
                sqlData += " ROWNUM <= :_endRow";
                paramMap.addValue("_endRow", offset + pageSize);
                if (null != orderBy && !"".equals(orderBy)) {
                    sqlData += " ORDER BY " + orderBy;
                }
                sqlData += " ) WHERE rn > :_startRow";
                paramMap.addValue("_startRow", offset);
                break;
            }
            case "db2" : {
                sqlData = "SELECT * FROM (SELECT ROW_NUMBER() OVER( ";
                if (null != orderBy && !"".equals(orderBy)) {
                    sqlData += " ORDER BY " + orderBy;
                }
                sqlData += " ) AS rownum, t.* FROM " + getTableName() + " t) AS temp WHERE ";
                if (null != condition && !"".equals(condition)) {
                    sqlData += condition + " AND ";
                    sqlCount += " WHERE " + condition;
                }
                sqlData += " rownum BETWEEN :_startRow AND :_endRow ";
                paramMap.addValue("_startRow", offset + 1);
                paramMap.addValue("_endRow", offset + pageSize);
                break;
            }
            case "sqlserver" : {
                sqlData = "SELECT * FROM (SELECT ROW_NUMBER() OVER(";
                if (null != orderBy && !"".equals(orderBy)) {
                    sqlData += "ORDER BY " + orderBy;
                }
                sqlData += ") AS rownum, * FROM " + getTableName() + ") AS temp WHERE ";
                if (null != condition && !"".equals(condition)) {
                    sqlData += condition + " AND ";
                    sqlCount += " WHERE " + condition;
                }
                sqlData += " rownum BETWEEN :_startRow AND :_endRow ";
                paramMap.addValue("_startRow", offset + 1);
                paramMap.addValue("_endRow", offset + pageSize);
                break;
            }
            case "mysql", "postgres", "postgresql" :  {
                sqlData = "SELECT * FROM " + getTableName();
                if (null != condition && !"".equals(condition)) {
                    sqlData += "WHERE " + condition;
                    sqlCount += " WHERE " + condition;
                }
                if (null != orderBy && !"".equals(orderBy)) {
                    sqlData += "ORDER BY " + orderBy;
                }
                sqlData += " LIMIT :_limit OFFSET :_offset ";
                paramMap.addValue("_limit", pageSize);
                paramMap.addValue("_offset", offset);
                break;
            }
            default:{
                throw new IllegalArgumentException("Unsupported database type: " + dbType);
            }
        }

        Integer countResult = namedParameterJdbcTemplate.queryForObject(sqlCount, paramMap, Integer.class);
        int totalCount = countResult != null ? countResult : 0;
        List<T> dataList = namedParameterJdbcTemplate.query(sqlData, paramMap, rowMapper);
        return new PageResult<>(dataList, totalCount, page, pageSize);
    }


    protected abstract String getInsertColumns();

    protected abstract String getInsertValues();

    protected abstract String getUpdateColumns();

    protected abstract String getTableName();

    protected abstract String getPrimaryKeyCondition();

}
