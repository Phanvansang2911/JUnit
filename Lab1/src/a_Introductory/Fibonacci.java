package a_Introductory;

public class Fibonacci {
	// FIXED: bỏ "+ 1" ở default
	public int fib(int n) {
		switch (n) {
			case 0: return 0;
			case 1: return 1;
			default: return (fib(n - 1) + fib(n - 2));
		}
	}
}