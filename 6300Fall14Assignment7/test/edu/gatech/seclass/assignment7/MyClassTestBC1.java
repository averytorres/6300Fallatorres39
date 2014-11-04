package edu.gatech.seclass.assignment7;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyClassTestBC1 {

	// MyClassTestBC1 should achieve 100% branch coverage of buggyMethod1 and
	// reveal the fault therein.
	@Test
	public void testbuggyMethod1_Branch1() {
		MyClass m = new MyClass();
		int expectedResult = 1;
		int actualResult = m.buggyMethod1(1, 1);
		assertEquals(expectedResult, actualResult);
	}

	// By “reveal the fault therein”, we mean that you should let the tests that
	// cause the division by zero fail with an uncaught exception, so that they
	// are easy to spot.
	@Test
	public void testbuggyMethod1_Branch2() {
		MyClass m = new MyClass();
		int expectedResult = 1;
		int actualResult = m.buggyMethod1(2, 0);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testbuggyMethod1_Branch3() {
		MyClass m = new MyClass();
		int expectedResult = 2;
		int actualResult = m.buggyMethod1(2, 3);
		assertEquals(expectedResult, actualResult);
	}
}
