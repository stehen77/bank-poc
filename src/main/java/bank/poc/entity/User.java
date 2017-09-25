package bank.poc.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

	private long id;
	private String firstname;
	private String lastname;
	private Integer age;
	private String address;
	private String phone;
	
	List<Account> accounts = new ArrayList<>();
	
	
	public String getFirstname() {
		return firstname;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long userId) {
		this.id = userId;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String adress) {
		this.address = adress;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	
}
