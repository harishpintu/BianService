package com.bian.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bian.model.Transaction;

public class TrasactionRowMapper implements RowMapper<Transaction> {
	
		@Override
		public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
			Transaction transaction = new Transaction();
			transaction.setAccountNumber(rs.getString("accountNumber"));
			transaction.setTransactionTs(rs.getString("transactionTs"));
			transaction.setType(rs.getString("type"));
			transaction.setAmount(rs.getString("amount"));
            return transaction;
		}
}
