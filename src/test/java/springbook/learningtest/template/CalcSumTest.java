package springbook.learningtest.template;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	// 3-36
	Calculator calculator;
	String numFilePath;
	
	@Before public void setUp() {
		this.calculator = new Calculator();
		this.numFilePath = getClass().getResource("numbers.txt").getPath();
	}
	
	@Test
	public void sumOfNumbers() throws IOException {
		// 3-30
		assertThat(calculator.calcSum(this.numFilePath), is(10));
	}
	@Test
	public void multiplyOfNumbers() throws IOException {
		// 3-30
		assertThat(calculator.calcMultiply(this.numFilePath), is(24));
	}

	
	@Test
	public void simpleTest() {
		String a = "a";
		System.out.println("simple test = " + ((a="hi").getClass()) );
	}
	
	
	
}

