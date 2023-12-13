package com.smallworld;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;

public class MainClass extends TransactionDataFetcher {

	private List<Transaction> transactionsList;

	public MainClass() {
		initView();
	}

	private void initView() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			transactionsList = Arrays.asList(mapper.readValue(new File("transactions.json"), Transaction[].class));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public double getTotalTransactionAmount() {

		return transactionsList.stream().mapToDouble(m -> m.getAmount()).sum();
	}

	@Override
	public double getTotalTransactionAmountSentBy(String senderFullName) {

		return transactionsList.stream().filter(f -> f.getSenderFullName().equals(senderFullName))
				.mapToDouble(m -> m.getAmount()).sum();
	}

	@Override
	public double getMaxTransactionAmount() {

		return transactionsList.stream().mapToDouble(m -> m.getAmount()).max().orElse(0.0);
	}

	@Override
	public long countUniqueClients() {

		Map<String, Integer> names = new HashMap<>();
		transactionsList.stream().map(m -> m.getBeneficiaryFullName()).forEach(name -> {
			if (names.containsKey(name)) {
				names.put(name, names.get(name) + 1);
			} else {
				names.put(name, 1);
			}
		});
		transactionsList.stream().map(m -> m.getSenderFullName()).forEach(name -> {
			if (names.containsKey(name)) {
				names.put(name, names.get(name) + 1);
			} else {
				names.put(name, 1);
			}
		});

		return names.entrySet().stream().filter(entry -> entry.getValue() == 1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).size();

	}

	@Override
	public boolean hasOpenComplianceIssues(String clientFullName) {

		boolean isSolved = transactionsList.stream().filter(m -> m.getBeneficiaryFullName().equals(clientFullName))
				.anyMatch(a -> a.isIssueSolved() == true);

		if (!isSolved) {
			return isSolved;
		}

		isSolved = transactionsList.stream().filter(m -> m.getSenderFullName().equals(clientFullName))
				.anyMatch(a -> a.isIssueSolved() == true);

		if (!isSolved) {
			return isSolved;
		}

		return true;

	}

	@Override
	public Map<String, Transaction> getTransactionsByBeneficiaryName() {

		Map<String, Transaction> names = new HashMap<>();
		transactionsList.stream().forEach(transaction -> {
			names.put(transaction.getBeneficiaryFullName(), transaction);
		});

		return names;
	}

	@Override
	public Set<Integer> getUnsolvedIssueIds() {

		return transactionsList.stream().filter(f -> f.isIssueSolved() == false).map(m -> m.getIssueId())
				.collect(Collectors.toSet());
		
	}

	@Override
	public List<String> getAllSolvedIssueMessages() {
		
		return transactionsList.stream().filter(f -> f.isIssueSolved() == true).map(m -> m.getIssueMessage())
				.collect(Collectors.toList());
	}

	@Override
	public List<Transaction> getTop3TransactionsByAmount() {
		// TODO Auto-generated method stub
		
		List<Transaction> top3Objects = transactionsList.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
		
		return top3Objects;
	}

	@Override
	public Optional<String> getTopSender() {
		
		Map<String, Double> names = new HashMap<>();
		transactionsList.stream().forEach(transaction -> {
			String beneficiaryName = transaction.getBeneficiaryFullName();
			if(names.containsKey(beneficiaryName)) {
				double previousAmount = names.get(beneficiaryName);
				names.put(beneficiaryName, transaction.getAmount() + previousAmount);
			} else {
				names.put(beneficiaryName, transaction.getAmount());
			}
		});
		
        return Optional.of(Collections.max(names.entrySet(), Map.Entry.comparingByValue()).getKey());

	}

	public static void main(String[] args) {
		MainClass t = new MainClass();

		System.out.println("getTotalTransactionAmount : " + t.getTotalTransactionAmount());
		System.out.println("getTotalTransactionAmountSentBy : " + t.getTotalTransactionAmountSentBy("Tom Shelby"));
		System.out.println("getMaxTransactionAmount : " + t.getMaxTransactionAmount());
		System.out.println("countUniqueClients : " + t.countUniqueClients());
		System.out.println("hasOpenComplianceIssues : " + t.hasOpenComplianceIssues("Aberama Gold"));
		System.out.println("getTransactionsByBeneficiaryName : " + t.getTransactionsByBeneficiaryName());
		System.out.println("getUnsolvedIssueIds : " + t.getUnsolvedIssueIds());
		System.out.println("getAllSolvedIssueMessages : " + t.getAllSolvedIssueMessages());
		System.out.println("getTop3TransactionsByAmount : " + t.getTop3TransactionsByAmount());
		System.out.println("getTopSender : " + t.getTopSender());

	}
}
