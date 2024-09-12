package qspider.bank.entity;

import java.time.LocalDate;

public class Transaction {
	private LocalDate transactionDate;
	private String transcationUserId;
	private Double transactionAmount;
	private String transactionType;
	private Double initialBalance;
	private Double finalbalance;
	private String transactionPerformedBy;

	public Transaction(LocalDate transactionDate, String transcationUserId, Double transactionAmount,
			String transactionType, Double initialBalance, Double finalbalance, String transactionPerformedBy) {
		super();
		this.transactionDate = transactionDate;
		this.transcationUserId = transcationUserId;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.initialBalance = initialBalance;
		this.finalbalance = finalbalance;
		this.transactionPerformedBy = transactionPerformedBy;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTranscationUserId() {
		return transcationUserId;
	}

	public void setTranscationUserId(String transcationUserId) {
		this.transcationUserId = transcationUserId;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(Double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public Double getFinalbalance() {
		return finalbalance;
	}

	public void setFinalbalance(Double finalbalance) {
		this.finalbalance = finalbalance;
	}

	public String getTransactionPerformedBy() {
		return transactionPerformedBy;
	}

	public void setTransactionPerformedBy(String transactionPerformedBy) {
		this.transactionPerformedBy = transactionPerformedBy;
	}

	@Override
	public String toString() {
		return "Transaction {transactionDate=" + transactionDate + ", transcationUserId=" + transcationUserId
				+ ", transactionAmount=" + transactionAmount + ", transactionType=" + transactionType
				+ ", initialBalance=" + initialBalance + ", finalbalance=" + finalbalance + ", transactionPerformedBy="
				+ transactionPerformedBy + "}";
	}
}
