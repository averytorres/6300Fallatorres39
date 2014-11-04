package edu.gatech.seclass.assignment7;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyClassTestSC1 {

	// MyClassTestSC1 should achieve 100% statement coverage of buggyMethod1 and
	// not reveal the fault therein.
	@Test
	public void testbuggyMethod1_Statement1() {
		MyClass m = new MyClass();
		int expectedResult = 1;
		int actualResult = m.buggyMethod1(1, 1);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testbuggyMethod1_Statement2() {
		MyClass m = new MyClass();
		int expectedResult = 2;
		int actualResult = m.buggyMethod1(2, 3);
		assertEquals(expectedResult, actualResult);
	}

}
