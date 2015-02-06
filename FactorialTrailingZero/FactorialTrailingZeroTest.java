package FactorialTrailingZero;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTrailingZeroTest {
	@Test
	public void test5() {
		FactorialTrailingZero factorialTrailingZero = new FactorialTrailingZero();
		Assert.assertEquals(factorialTrailingZero.trailingZeros(5), 1);
	}
	
	@Test
	public void test4() {
		FactorialTrailingZero factorialTrailingZero = new FactorialTrailingZero();
		Assert.assertEquals(factorialTrailingZero.trailingZeros(4), 0);
	}
	
	@Test
	public void test30() {
		FactorialTrailingZero factorialTrailingZero = new FactorialTrailingZero();
		Assert.assertEquals(factorialTrailingZero.trailingZeros(30), 7);	
	}
	
	@Test
	public void test2147483647() {
		FactorialTrailingZero factorialTrailingZero = new FactorialTrailingZero();
		Assert.assertEquals(factorialTrailingZero.trailingZeros(2147483647), 536870902);			
	}

}
