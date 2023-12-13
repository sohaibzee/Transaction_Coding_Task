package com.smallworld.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.smallworld.MainClass;
import com.smallworld.data.Transaction;

public class MainClassTest {

	@Test
	public void testGetTotalTransactionAmount() {
		
		MainClass main = new MainClass();
		
		double amount = main.getTotalTransactionAmount();
        assertEquals(4371.37, amount, "Invalid result!");
        
	}
	
	@Test
	public void testGetTotalTransactionAmountSentBy() {
		
		MainClass main = new MainClass();
		
		double amount = main.getTotalTransactionAmountSentBy("Tom Shelby");
        assertEquals(828.26, amount, "Invalid result!");
        
	}
	
	@Test
	public void testGetMaxTransactionAmount() {
		
		MainClass main = new MainClass();
		
		double amount = main.getMaxTransactionAmount();
        assertEquals(985.0, amount, "Invalid result!");
        
	}
	
	@Test
	public void testCountUniqueClients() {
		
		MainClass main = new MainClass();
		
		long amount = main.countUniqueClients();
        assertEquals(8, amount, "Invalid result!");
        
	}
	
	@Test
	public void testHasOpenComplianceIssues() {
		
		MainClass main = new MainClass();
		
		boolean hasOpenComplianceIssue = main.hasOpenComplianceIssues("Aberama Gold");
        assertEquals(true, hasOpenComplianceIssue, "Invalid result!");
        
	}
	
	@Test
	public void testGetTransactionsByBeneficiaryName() {
		
		MainClass main = new MainClass();
		
		Map<String, Transaction> map = main.getTransactionsByBeneficiaryName();
        assertNotNull(map, "Result should not be null");
        
	}
	
	@Test
	public void testGetUnsolvedIssueIds() {
		
		MainClass main = new MainClass();
		
		Set<Integer> set = main.getUnsolvedIssueIds();
        assertNotNull(set, "Result should not be null");
        
	}
	
	@Test
	public void testGetAllSolvedIssueMessages() {
		
		MainClass main = new MainClass();
		
		List<String> list = main.getAllSolvedIssueMessages();
        assertNotNull(list, "Result should not be null");
        
	}
	
	@Test
	public void testGetTop3TransactionsByAmount() {
		
		MainClass main = new MainClass();
		
		List<Transaction> list = main.getTop3TransactionsByAmount();
        assertNotNull(list, "Result should not be null");
        
	}
	
	@Test
	public void testGetTopSender() {
		
		MainClass main = new MainClass();
		
		Optional<String> sender = main.getTopSender();
        assertNotNull(sender, "Result should not be null");

	}
}
