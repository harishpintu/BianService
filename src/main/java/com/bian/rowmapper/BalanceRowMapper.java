package com.bian.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bian.model.Balance;

public class BalanceRowMapper implements RowMapper<Balance> {
	
		@Override
		public Balance mapRow(ResultSet rs, int rowNum) throws SQLException {
			Balance balance = new Balance();
			balance.setAccountNumber(rs.getString("accountNumber"));
			balance.setLastUpdateTimestamp(rs.getString("lastUpdateTimestamp"));
			balance.setBalance(rs.getString("balance"));
            return balance;
		}
}
