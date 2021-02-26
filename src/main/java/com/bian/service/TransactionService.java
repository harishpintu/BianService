package com.bian.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bian.entity.TransactionsByDateBody;
import com.bian.entity.TransactionsByTypeBody;
import com.bian.model.Transaction;

@Service
public interface TransactionService {
	
	public void addTransaction(String transactionJson);
	public List<Transaction> getTransactionsByDate(TransactionsByDateBody transactionsByDateBody);
	public List<Transaction> getTransactionsByDateAndType(TransactionsByTypeBody transactionsByTypeBody);

}
