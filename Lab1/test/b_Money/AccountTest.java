package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		SweBank.deposit("Alice", new Money(1000000, SEK));
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("pay1", 10, 0, new Money(50000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("pay1"));
		testAccount.removeTimedPayment("pay1");
		assertFalse(testAccount.timedPaymentExists("pay1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// interval=2, next=0 -> thanh toán ngay tick đầu
		testAccount.addTimedPayment("pay2", 2, 0, new Money(100000, SEK), SweBank, "Alice");
		
		testAccount.tick(); // thanh toán lần 1
		assertEquals(9900000, (int)testAccount.getBalance().getAmount());
		assertEquals(1100000, (int)SweBank.getBalance("Alice"));
		
		testAccount.tick(); // giảm next, không thanh toán
		assertEquals(9900000, (int)testAccount.getBalance().getAmount());
		testAccount.tick(); // tiếp tục giảm
		assertEquals(9900000, (int)testAccount.getBalance().getAmount());
		
		testAccount.tick(); // next=0 -> thanh toán lần 2
		assertEquals(9800000, (int)testAccount.getBalance().getAmount());
		assertEquals(1200000, (int)SweBank.getBalance("Alice"));
	}

	@Test
	public void testAddWithdraw() {
		testAccount.deposit(new Money(500000, SEK));
		assertEquals(10500000, (int)testAccount.getBalance().getAmount());
		testAccount.withdraw(new Money(200000, SEK));
		assertEquals(10300000, (int)testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(10000000, (int)testAccount.getBalance().getAmount());
		assertEquals("SEK", testAccount.getBalance().getCurrency().getName());
	}
}