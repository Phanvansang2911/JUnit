package b_Money;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		// Mở tk mới không ném ngoại lệ
		SweBank.openAccount("NewAcc");
		assertNotNull(SweBank.getBalance("NewAcc"));
		// Mở lại sẽ ném AccountExistsException
		try {
			SweBank.openAccount("NewAcc");
			fail("Should throw AccountExistsException");
		} catch (AccountExistsException e) {
			// expected
		}
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(50000, SEK));
		assertEquals(50000, (int)SweBank.getBalance("Ulrika"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(100000, SEK));
		SweBank.withdraw("Bob", new Money(30000, SEK));
		assertEquals(70000, (int)SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(0, (int)SweBank.getBalance("Ulrika")); // mặc định 0
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(100000, SEK));
		SweBank.transfer("Bob", Nordea, "Bob", new Money(40000, SEK));
		assertEquals(60000, (int)SweBank.getBalance("Bob"));
		assertEquals(40000, (int)Nordea.getBalance("Bob"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(200000, SEK));
		SweBank.addTimedPayment("Ulrika", "pay1", 1, 0, new Money(50000, SEK), Nordea, "Bob");
		SweBank.tick();
		assertEquals(150000, (int)SweBank.getBalance("Ulrika"));
		assertEquals(50000, (int)Nordea.getBalance("Bob"));
	}
}