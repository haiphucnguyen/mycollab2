package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.common.domain.Currency;

public class SimpleProject extends Project {
	private static final long serialVersionUID = 1L;

	private String ownerName;

	private String accountName;

	private int numOpenBugs;

	private int numBugs;

	private int numOpenTasks;

	private int numTasks;

	private int numOpenProblems;

	private int numProblems;

	private int numOpenRisks;

	private int numRisks;
	
	private Currency currency;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getNumOpenBugs() {
		return numOpenBugs;
	}

	public void setNumOpenBugs(int numOpenBugs) {
		this.numOpenBugs = numOpenBugs;
	}

	public int getNumBugs() {
		return numBugs;
	}

	public void setNumBugs(int numBugs) {
		this.numBugs = numBugs;
	}

	public int getNumOpenTasks() {
		return numOpenTasks;
	}

	public void setNumOpenTasks(int numOpenTasks) {
		this.numOpenTasks = numOpenTasks;
	}

	public int getNumTasks() {
		return numTasks;
	}

	public void setNumTasks(int numTasks) {
		this.numTasks = numTasks;
	}

	public int getNumOpenProblems() {
		return numOpenProblems;
	}

	public void setNumOpenProblems(int numOpenProblems) {
		this.numOpenProblems = numOpenProblems;
	}

	public int getNumProblems() {
		return numProblems;
	}

	public void setNumProblems(int numProblems) {
		this.numProblems = numProblems;
	}

	public int getNumOpenRisks() {
		return numOpenRisks;
	}

	public void setNumOpenRisks(int numOpenRisks) {
		this.numOpenRisks = numOpenRisks;
	}

	public int getNumRisks() {
		return numRisks;
	}

	public void setNumRisks(int numRisks) {
		this.numRisks = numRisks;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
