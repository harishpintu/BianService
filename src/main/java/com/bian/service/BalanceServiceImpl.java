package com.bian.service;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bian.model.Balance;
import com.bian.repository.BainRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class BalanceServiceImpl implements BalanceService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private BainRepository repository;

	String kafkaTopic = "balances-topic";

	@Override
	public void addBalance(String jsonString) {

		kafkaTemplate.send(kafkaTopic, jsonString);
	}

	@KafkaListener(topics = "balances-topic")
	public void consume(String message) throws IOException{
		try {

			ObjectMapper mapper = new ObjectMapper();
			Balance balance = mapper.readValue(message, Balance.class);
			repository.addBalance(balance);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Balance> getbalance(String accountNumber) {

		List<Balance> balanceList = repository.getBalance(accountNumber);
		return balanceList;
	}
}