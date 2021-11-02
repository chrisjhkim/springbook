
package springbook.learningtest.template;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class ClacSumTest {
	
	@Test
	public void sumOfNumbers() throws IOException {
		// 3-30
		Calculator calculator = new Calculator();
//		System.out.println(getClass());
//		System.out.println(getClass().getResource("numbers.txt"));
		int sum = calculator.calcSum(getClass().getResource("numbers.txt").getPath());
		assertEquals(10, sum);
	}

	
	@Test
	public void simpleTest() {
		String a = "a";
		System.out.println("simple test = " + ((a="hi").getClass()) );
	}
	
	
	
}

