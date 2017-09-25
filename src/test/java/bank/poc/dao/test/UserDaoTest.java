package bank.poc.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import bank.poc.dao.AccountDao;
import bank.poc.dao.UserDao;
import bank.poc.entity.Account;
import bank.poc.entity.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
	
	private UserDao userDao = new UserDao();
	private AccountDao accountDao = new AccountDao();
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void test1CreateUser() {
		User user = new User();
		user.setId(1);
		user.setFirstname("Dupont");
		user.setLastname("Pierre");
		user.setAddress("addresse");
		user.setAge(40);
		user.setPhone("01-01-01-01-01");
		
		
		assertNull(userDao.getUserById(1));
		userDao.addUser(user);
		User addedUser = userDao.getUserById(1);
		assertNotNull(addedUser);
		
		assertEquals(user.getFirstname(), addedUser.getFirstname());
		assertEquals(user.getLastname(), addedUser.getLastname());
		assertEquals(user.getAddress(), addedUser.getAddress());
		assertEquals(user.getAge(), addedUser.getAge());
		assertEquals(user.getPhone(), addedUser.getPhone());
	
	}
	
	
	@Test
	public void test2UpdateUser() {
		User user = userDao.getUserById(1);
		assertNotNull(user);
		assertEquals("Dupont", user.getFirstname());
		assertEquals("Pierre", user.getLastname());
		
		user.setFirstname("Madeleine");
		user.setLastname("Jean");
		userDao.updateUser(user);
		
		User updatedUser = userDao.getUserById(1);
		assertNotNull(updatedUser);
		assertEquals("Madeleine", updatedUser.getFirstname());
		assertEquals("Jean", updatedUser.getLastname());
	}
	
	
	@Test
	public void test3LinkAccountToUser() {
		
		User user = userDao.getUserById(1);
		assertNotNull(user);
		assertTrue(CollectionUtils.isEmpty(user.getAccounts()));
		
		Account account = new Account();
		account.setId(1);
		account.setNumber(123);
		account.setCreationDate(new Date());
		account.setBalance(50);
		accountDao.addAccount(account);
		userDao.linkAccountToUser(1, 1);
		
		User linkedUser = userDao.getUserById(1);
		assertNotNull(linkedUser);
		List<Account> accounts = user.getAccounts();
		assertTrue(CollectionUtils.isNotEmpty(accounts));
		assertEquals(1, CollectionUtils.size(accounts));
		Account linkedAccount = accounts.get(0);
		assertEquals(1, linkedAccount.getId());
		assertEquals(50, linkedAccount.getBalance(), 1e-15);
		assertEquals(123, linkedAccount.getNumber());
		
	}
	
	@Test
	public void test4LinkAccountToUser() {
		List<Account> accounts = userDao.getUserAccounts(1);
		assertTrue(CollectionUtils.isNotEmpty(accounts));
		assertEquals(1, CollectionUtils.size(accounts));
	}
	
	@Test
	public void test5SumBalanceAccounts() {
		double balance = userDao.getSumBalanceAccounts(1);
		//double DELTA (pour comparer deux double)
		assertEquals(50, balance, 1e-15);
	}
	
	
	@Test
	public void test6DeleteUser() {
		assertNotNull(userDao.getUserById(1));
		userDao.deleteUser(1);
		assertNull(userDao.getUserById(1));
	}
	

}
