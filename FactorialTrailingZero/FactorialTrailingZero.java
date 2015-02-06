package FactorialTrailingZero;

public class FactorialTrailingZero {
	public int trailingZeros(int n) {
		long root = 5;
		int numberOf5 = 0;
		while (n >= root) {
			numberOf5 = numberOf5 + (int)(n/root);
			root = root*5;
		}
		return numberOf5;
	}
}
