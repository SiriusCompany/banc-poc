package com.sirius.io.sob9m01002.dao;

import com.sirius.io.dao.GenericDao;
import com.sirius.io.sob9m01002.entity.BalanceAccumulationHistory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceAccumulationHistoryDAO extends GenericDao<BalanceAccumulationHistory> {
    public BalanceAccumulationHistoryDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate, new BeanPropertyRowMapper<>(BalanceAccumulationHistory.class));
    }

    @Override
    public MapSqlParameterSource getParameterSource(BalanceAccumulationHistory entity) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("transactionId", entity.getTransactionId());
        mapSqlParameterSource.addValue("idUser", entity.getIdUser());
        mapSqlParameterSource.addValue("idAccount", entity.getIdAccount());
        mapSqlParameterSource.addValue("transactionAmount", entity.getTransactionAmount());
        mapSqlParameterSource.addValue("balance", entity.getBalance());
        mapSqlParameterSource.addValue("retCode", entity.getRetCode());
        mapSqlParameterSource.addValue("retMessage", entity.getRetMessage());
        mapSqlParameterSource.addValue("createTime", entity.getCreateTime());

        return mapSqlParameterSource;
    }

    @Override
    protected String getInsertColumns() {
        return "transaction_id,id_user,id_account,transaction_amount,balance,ret_code,ret_message,create_time";
    }

    @Override
    protected String getInsertValues() {
        return ":transactionId,:idUser,:idAccount,:transactionAmount,:balance,:retCode,:retMessage,:createTime";
    }

    @Override
    protected String getUpdateColumns() {
        return "transaction_id = :transactionId,id_user = :idUser,id_account = :idAccount,transaction_amount = :transactionAmount,balance = :balance,ret_code = :retCode,ret_message = :retMessage,create_time = :createTime";
    }

    @Override
    protected String getTableName() {
        return " balance_accumulation_history ";
    }

    @Override
    protected String getPrimaryKeyCondition() {
        return " transaction_id = :transactionId ";
    }
}
