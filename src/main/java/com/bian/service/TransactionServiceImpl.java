package com.bian.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bian.entity.TransactionsByDateBody;
import com.bian.entity.TransactionsByTypeBody;
import com.bian.model.Transaction;
import com.bian.repository.BainRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private BainRepository repository;

	String kafkaTopic = "transactions-topic";
	List<Transaction> transactionList;

	@Override
	public void addTransaction(String transactionJson) {
		kafkaTemplate.send(kafkaTopic, transactionJson);
	}

	@KafkaListener(topics = "transactions-topic")
	public void consume(String message) throws IOException{
		try {

			ObjectMapper mapper = new ObjectMapper();
			Transaction transaction = mapper.readValue(message, Transaction.class);
			repository.addTransaction(transaction);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Transaction> getTransactionsByDate(TransactionsByDateBody transactionsByDateBody) {

		List<Transaction> transactionList = repository.getTransactionsByDate(transactionsByDateBody);
		return transactionList;
	}

	@Override
	public List<Transaction> getTransactionsByDateAndType(TransactionsByTypeBody transactionsByTypeBody){

		List<Transaction> transactionList = repository.getTransactionsByDateAndType(transactionsByTypeBody);
		return transactionList;

	}
}