package b_Money;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals((Integer)10000, SEK100.getAmount());
		assertEquals((Integer)1000, EUR10.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
		assertEquals("10.0 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals((Integer)1500, SEK100.universalValue());   // 10000 * 0.15 = 1500
		assertEquals((Integer)1500, EUR10.universalValue());    // 1000 * 1.5 = 1500
	}

	@Test
	public void testEqualsMoney() {
		// 10000 SEK = 1500 universal, 1000 EUR = 1500 universal -> bằng nhau
		assertTrue(SEK100.equals(EUR10));
		assertFalse(SEK100.equals(SEK200));
	}

	@Test
	public void testAdd() {
		Money sum = SEK100.add(EUR10);
		// SEK100 + EUR10 = 2000 universal -> SEK amount = 2000/0.15 ≈ 13333
		assertEquals(13333, (int)sum.getAmount());
		assertEquals(SEK, sum.getCurrency());
	}

	@Test
	public void testSub() {
		Money diff = SEK200.sub(EUR10);
		// 20000 SEK - 1000 EUR = 3000 universal - 1500 universal = 1500 universal => SEK 10000
		assertEquals(10000, (int)diff.getAmount());
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
		assertFalse(SEK100.isZero());
	}

	@Test
	public void testNegate() {
		Money neg = SEK100.negate();
		assertEquals(-10000, (int)neg.getAmount());
		assertEquals(SEK, neg.getCurrency());
	}

	@Test
	public void testCompareTo() {
		assertTrue(SEK100.compareTo(EUR10) == 0);   // bằng nhau
		assertTrue(SEK100.compareTo(SEK200) < 0);   // 100 < 200
		assertTrue(SEK200.compareTo(SEK100) > 0);
	}
}