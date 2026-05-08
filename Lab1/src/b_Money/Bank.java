package b_Money;

import java.util.Hashtable;

public class Bank {
	private Hashtable<String, Account> accountlist = new Hashtable<String, Account>();
	private String name;
	private Currency currency;
	
	Bank(String name, Currency currency) {
		this.name = name;
		this.currency = currency;
	}
	
	public String getName() {
		return name;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	// FIXED: thêm tài khoản vào danh sách (trước đây chỉ lấy ra vô ích)
	public void openAccount(String accountid) throws AccountExistsException {
		if (accountlist.containsKey(accountid)) {
			throw new AccountExistsException();
		}
		else {
			accountlist.put(accountid, new Account(accountid, currency));
		}
	}
	
	// FIXED: đảo điều kiện kiểm tra tồn tại
	public void deposit(String accountid, Money money) throws AccountDoesNotExistException {
		if (!accountlist.containsKey(accountid)) {
			throw new AccountDoesNotExistException();
		}
		else {
			Account account = accountlist.get(accountid);
			account.deposit(money);
		}
	}
	
	// FIXED: gọi withdraw thay vì deposit
	public void withdraw(String accountid, Money money) throws AccountDoesNotExistException {
		if (!accountlist.containsKey(accountid)) {
			throw new AccountDoesNotExistException();
		}
		else {
			Account account = accountlist.get(accountid);
			account.withdraw(money);
		}
	}
	
	public Integer getBalance(String accountid) throws AccountDoesNotExistException {
		if (!accountlist.containsKey(accountid)) {
			throw new AccountDoesNotExistException();
		}
		else {
			return accountlist.get(accountid).getBalance().getAmount();
		}
	}

	public void transfer(String fromaccount, Bank tobank, String toaccount, Money amount) throws AccountDoesNotExistException {
		if (!accountlist.containsKey(fromaccount) || !tobank.accountlist.containsKey(toaccount)) {
			throw new AccountDoesNotExistException();
		}
		else {
			accountlist.get(fromaccount).withdraw(amount);
			tobank.accountlist.get(toaccount).deposit(amount);
		}		
	}

	// FIXED: truyền đúng toaccount thay vì fromaccount
	public void transfer(String fromaccount, String toaccount, Money amount) throws AccountDoesNotExistException {
		transfer(fromaccount, this, toaccount, amount);
	}

	// FIXED: kiểm tra tài khoản tồn tại trước khi thêm timed payment
	public void addTimedPayment(String accountid, String payid, Integer interval, Integer next, Money amount, Bank tobank, String toaccount) throws AccountDoesNotExistException {
		Account account = accountlist.get(accountid);
		if (account == null) {
			throw new AccountDoesNotExistException();
		}
		account.addTimedPayment(payid, interval, next, amount, tobank, toaccount);
	}
	
	// FIXED: kiểm tra tài khoản tồn tại trước khi xoá
	public void removeTimedPayment(String accountid, String id) throws AccountDoesNotExistException {
		Account account = accountlist.get(accountid);
		if (account == null) {
			throw new AccountDoesNotExistException();
		}
		account.removeTimedPayment(id);
	}
	
	public void tick() throws AccountDoesNotExistException {
		for (Account account : accountlist.values()) {
			account.tick();
		}
	}	
}