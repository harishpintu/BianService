package com.bian.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bian.model.Balance;
import com.bian.service.BalanceService;
import com.bian.entity.BalanceRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="bain")
public class BalanceResource {

	@Autowired
	BalanceService balanceService;

	@GetMapping(value="/addBalance")
	public @ResponseBody String addBalance(@RequestBody BalanceRequestBody balanceRequestBody) {
		ObjectMapper mapper = new ObjectMapper();
		String balanceJson = null;
		try {

			balanceJson = mapper.writeValueAsString(balanceRequestBody);
			balanceService.addBalance(balanceJson);
			return "Balance added to the Kafka Topic balances_topic Successfully";

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "internal server error";
		}

	}

	@GetMapping(value="/getbalance")
	public ResponseEntity<List> getbalance(@RequestParam("accountNumber") String accountNumber) {

		List<Balance> balanceList = new ArrayList<Balance>();
		balanceList = balanceService.getbalance(accountNumber);

		return new ResponseEntity<>(balanceList, HttpStatus.OK);

	}
}