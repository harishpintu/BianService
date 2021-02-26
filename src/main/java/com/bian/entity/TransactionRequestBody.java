package com.bian.entity;

public class TransactionRequestBody {

	private String accountNumber;
	private String transactionTs;
	private String type;
	private String amount;
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @return the transactionTs
	 */
	public String getTransactionTs() {
		return transactionTs;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * @param transactionTs the transactionTs to set
	 */
	public void setTransactionTs(String transactionTs) {
		this.transactionTs = transactionTs;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
