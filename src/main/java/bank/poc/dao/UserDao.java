package bank.poc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import bank.poc.entity.Account;
import bank.poc.entity.User;

public class UserDao {

	private static List<User> users = new ArrayList<>();
	
	private AccountDao accountDao;
	
	/**
	 * Constructor
	 */
	public UserDao() {
		accountDao = new AccountDao();
	}

	
	public User getUserById(long id) {
		return users.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);
	}

	public User getUserByName(String lastName, String firstName) {
		return users.stream()
				.filter(u -> (StringUtils.equals(lastName, u.getLastname()) && StringUtils.equals(firstName, u.getFirstname())))
				.findFirst()
				.orElse(null);
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void updateUser(User user) {
		users.stream()
		.filter(u -> user.getId() == u.getId())
		.findFirst()
		.ifPresent(u -> u = user);
	}
	
	public void deleteUser(long id) {
		User user = users.stream()
				.filter(u -> id == u.getId())
				.findFirst()
				.orElse(null);
		
		if (user != null) {
			users.remove(user);
		}
	}
	
	public void linkAccountToUser(long userId, long accountId) {
		getUserById(userId).getAccounts().add(accountDao.getAccountById(accountId));
	}
	
	public List<Account> getUserAccounts(long userId) {
		return getUserById(userId) != null ? getUserById(userId).getAccounts() : null;
	}
	
	public double getSumBalanceAccounts(long userId) {
		List<Account> accounts = getUserAccounts(userId);
		if (CollectionUtils.isNotEmpty(accounts)) {
			// transformer la liste des comptes en une liste de balance et ensuite je fais la somme
			return accounts.stream().mapToDouble(a -> a.getBalance()).sum();
		}
		return 0;
	}
}
