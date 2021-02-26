package com.bian.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bian.entity.TransactionsByDateBody;
import com.bian.entity.TransactionsByTypeBody;
import com.bian.model.Balance;
import com.bian.model.Transaction;
import com.bian.rowmapper.BalanceRowMapper;
import com.bian.rowmapper.TrasactionRowMapper;

@Repository
public class BainRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public List<Transaction> getTransactions() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			transactionList = jdbcTemplate.query("select * from Transaction", new TrasactionRowMapper());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return transactionList;
	}

	public List<Transaction> getTransactionsByDate(TransactionsByDateBody transactionsByDateBody) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			transactionList = jdbcTemplate.query("select * from Transaction where transactionts between  to_date('"+transactionsByDateBody.getStartDate()+"','yyyy-MM-dd') and to_date('"+transactionsByDateBody.getEndDate()+"','yyyy-MM-dd')", new TrasactionRowMapper());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return transactionList;
	}

	public List<Transaction> getTransactionsByDateAndType(TransactionsByTypeBody transactionsByTypeBody) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			transactionList = jdbcTemplate.query("select * from Transaction where upper(type) = upper('"+transactionsByTypeBody.getType()+"') and transactionts between  to_date('"+transactionsByTypeBody.getStartDate()+"','yyyy-MM-dd') and to_date('"+transactionsByTypeBody.getEndDate()+"','yyyy-MM-dd')", new TrasactionRowMapper());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return transactionList;
	}

	private LocalDate getFormatedDate(String transactionTs) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S'Z'", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(transactionTs, inputFormatter);
		return date;
	}

	public int addTransaction(Transaction transaction) {
		return jdbcTemplate.update("insert into Transaction (accountNumber, transactionTs, type,amount) " + "values(?, ?, ?, ?)",
				new Object[] {
						transaction.getAccountNumber(),getFormatedDate(transaction.getTransactionTs()),transaction.getType(), transaction.getAmount()
		});
	}

	public List<Balance> getBalance(String accountNumber) {
		List<Balance> balanceList = new ArrayList<Balance>();
		try {
			balanceList = jdbcTemplate.query("select * from Balance where accountNumber ="+accountNumber, new BalanceRowMapper());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return balanceList;
	}

	public int addBalance(Balance balance) {
		return jdbcTemplate.update("insert into Balance (accountNumber, lastUpdateTimestamp, balance) " + "values(?,?, ?,?)",
				new Object[] {
						balance.getAccountNumber(), balance.getLastUpdateTimestamp(), balance.getBalance()
		});
	}

}
