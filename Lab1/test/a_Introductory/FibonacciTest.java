package a_Introductory;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FibonacciTest {

    @Test
    public void testFib() {
        Fibonacci tester = new Fibonacci();

        assertEquals("0", Integer.valueOf(0), Integer.valueOf(tester.fib(0)));
        assertEquals("1", Integer.valueOf(1), Integer.valueOf(tester.fib(1)));
        assertEquals("2", Integer.valueOf(1), Integer.valueOf(tester.fib(2)));
        assertEquals("3", Integer.valueOf(2), Integer.valueOf(tester.fib(3)));
        assertEquals("4", Integer.valueOf(3), Integer.valueOf(tester.fib(4)));
        assertEquals("5", Integer.valueOf(5), Integer.valueOf(tester.fib(5)));
        assertEquals("6", Integer.valueOf(8), Integer.valueOf(tester.fib(6)));
        assertEquals("7", Integer.valueOf(13), Integer.valueOf(tester.fib(7)));
    }
}