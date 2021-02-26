package com.bian.resource;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bian.entity.TransactionRequestBody;
import com.bian.entity.TransactionsByDateBody;
import com.bian.entity.TransactionsByTypeBody;
import com.bian.model.Transaction;
import com.bian.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="bain")
public class TransactionResource {

	@Autowired
	TransactionService transactionService;

	@GetMapping(value="/addTransaction")
	public @ResponseBody String addTransaction(@RequestBody TransactionRequestBody transactionRequestBody) {
		ObjectMapper mapper = new ObjectMapper();
		String transactionJson = null;
		try {
			transactionJson = mapper.writeValueAsString(transactionRequestBody);
			
			transactionService.addTransaction(transactionJson);
		
			return "Trasaction added to the Kafka Topic Transactions_topic Successfully";
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "internal server error";
		}
	}

	@GetMapping(value="/getTransactionsByDate")
	public ResponseEntity<List> getTransactionsByDate(@RequestBody TransactionsByDateBody transactionsByDateBody) {

		List<Transaction> transactionList = transactionService.getTransactionsByDate(transactionsByDateBody);

		return new ResponseEntity<>(transactionList, HttpStatus.OK);

	}

	@GetMapping(value="/getTransactionsByDateAndType")
	public ResponseEntity<List> getTransactionsByDateAndType(@RequestBody TransactionsByTypeBody transactionsByTypebody) {

		List<Transaction> transactionList = transactionService.getTransactionsByDateAndType(transactionsByTypebody);

		return new ResponseEntity<>(transactionList, HttpStatus.OK);

	}
}