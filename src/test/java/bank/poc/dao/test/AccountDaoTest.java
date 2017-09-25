package bank.poc.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import bank.poc.dao.AccountDao;
import bank.poc.entity.Account;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDaoTest {

	private AccountDao accountDao= new AccountDao();
	
	@Test
	public void test1CreateAccount(){
		Account account = new Account();
		
		account.setId(1);
		account.setBalance(50);
		account.setCreationDate(new Date());
		account.setNumber(123);
		
		assertNull(accountDao.getAccountById(1));
		accountDao.addAccount(account);
		
		Account accountAdded = accountDao.getAccountById(1);
		assertNotNull(accountAdded);
		
		assertEquals(account.getBalance(), accountAdded.getBalance(), 1e-15);
		assertEquals(account.getCreationDate(), accountAdded.getCreationDate());
		assertEquals(account.getNumber(), accountAdded.getNumber());
	}
	
	@Test
	public void test2UpdateAccount() {
		
		Account account = accountDao.getAccountById(1);
		assertNotNull(account);
		assertEquals(50, account.getBalance(), 1e-15);
		assertEquals(123, account.getNumber());
		
		account.setBalance(60);
		account.setNumber(001);
		accountDao.updateAccount(account);
		
		Account accountUpdated = accountDao.getAccountById(1);
		assertNotNull(accountUpdated);
		
		assertEquals(60, accountUpdated.getBalance(), 1e-15);
		assertEquals(001, accountUpdated.getNumber());
		
	}
	
	@Test
	public void test3UpdateBalance(){
		Account account = accountDao.getAccountById(1);
		assertNotNull(account);
		
		assertEquals(60, account.getBalance(), 1e-15);
		
		
		accountDao.updateBalance(account.getId(),100);
		Account updatedBalance = accountDao.getAccountById(1);
		assertEquals(100, updatedBalance.getBalance(), 1e-15);
	}
	
	@Test
	public void test4DeleteAccount(){
		
		assertNotNull(accountDao.getAccountById(1));
		accountDao.deleteAccount(1);
		assertNull(accountDao.getAccountById(1));
	}
	
}
