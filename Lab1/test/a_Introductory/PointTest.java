package a_Introductory;

import org.junit.Before;
import org.junit.Test;   // NOTE: thêm import Test
import static org.junit.Assert.*;

public class PointTest {
	Point p1, p2, p3;
	
	@Before
	public void setUp() throws Exception {
		p1 = new Point(7, 9);
		p2 = new Point(-3, -30);
		p3 = new Point(-10, 3);
	}
	
	@Test   // NOTE: thêm @Test
	public void testAdd() {
		Point res1 = p1.add(p2);
		Point res2 = p1.add(p3);
		
		assertEquals(4, (int)res1.x);
		assertEquals(-21, (int)res1.y);
		assertEquals(-3, (int)res2.x);
		// FIXED: sửa từ res2.x thành res2.y
		assertEquals(12, (int)res2.y);
	}
	
	@Test   // NOTE: thêm @Test
	public void testSub() {
		Point res1 = p1.sub(p2);
		Point res2 = p1.sub(p3);
		
		assertEquals(10, (int)res1.x);     // 7 - (-3) = 10
		assertEquals(39, (int)res1.y);     // 9 - (-30) = 39
		assertEquals(17, (int)res2.x);     // 7 - (-10) = 17
		assertEquals(6, (int)res2.y);      // 9 - 3 = 6
	}
}