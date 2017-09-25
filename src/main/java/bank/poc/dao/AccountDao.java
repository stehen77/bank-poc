package bank.poc.dao;

import java.util.ArrayList;
import java.util.List;

import bank.poc.entity.Account;

public class AccountDao {
	
	private static List<Account> accounts = new ArrayList<>();
	
	
	public Account getAccountById(long id) {
		return accounts.stream()
				.filter(a -> a.getId() == id)
				.findFirst()
				.orElse(null);
	}
	
	public Account getAccountByNumber(long number) {
		return accounts.stream()
				.filter(a -> a.getNumber() == number)
				.findFirst()
				.orElse(null);
	}
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void updateAccount(Account account) {
		accounts.stream()
		.filter(a -> account.getId() == a.getId())
		.findFirst()
		.ifPresent(a -> a = account);
	}
	
	public void updateBalance(long accountId, double balance) {
		accounts.stream()
		.filter(a -> accountId == a.getId())
		.findFirst()
		.ifPresent(a -> a.setBalance(balance));
	}
	
	public void deleteAccount(long id) {
		Account account = accounts.stream()
				.filter(a -> id == a.getId())
				.findFirst()
				.orElse(null);
		
		if (account != null) {
			accounts.remove(account);
		}
	}

}
