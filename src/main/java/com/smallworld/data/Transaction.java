package com.smallworld.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
	// Represent your transaction data here.

	private int mtn;
	private double amount;
	private String senderFullName;
	private int senderAge;
	private String beneficiaryFullName;
	private int beneficiaryAge;
	private int issueId;
	private boolean issueSolved;
	private String issueMessage;
	
	
}
