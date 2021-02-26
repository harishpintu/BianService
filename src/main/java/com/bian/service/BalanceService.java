package com.bian.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.bian.model.Balance;


@Service
public interface BalanceService {

	public void addBalance(String jsonString);
	public List<Balance> getbalance(String accountNumber);

}