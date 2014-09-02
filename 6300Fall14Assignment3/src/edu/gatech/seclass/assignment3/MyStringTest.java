package edu.gatech.seclass.assignment3;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyStringTest {

	@Test
	// This test is looking that see if the string returned when calling
	// getVowels() is an empty string
	// when the inital value has not been set.
	public void getVowels_UninitString_ShouldReturnEmptyString() {

		MyString m = new MyString();

		assertTrue(
				"Empty string should be returned when MyString value not initialized and getVowels() called",
				m.getVowels().equals(""));
	}

	@Test
	// This test ensures that vowels are returned for the test string.
	// The vowels returned will be in their original case as well as in their
	// initial order without spaces.
	public void getVowels_InitedString_ShouldReturnVowelsOnly() {

		MyString m = new MyString();
		m.setString("aeiou AEIOU bcdfghjklmnpqrstvwxyzaa");
		assertTrue(
				"Only vowels should be returned when calling the getVowels() method",
				m.getVowels().equals("aeiouAEIOUaa"));
	}

	@Test
	// This test ensures that the proper error message is given when the user
	// has not initialized the string and has called the getSubString() method.
	public void getSubstring_UninitedString_ShouldReturnError() {

		MyString m = new MyString();
		assertTrue(
				"Error message should be returned when getSubstring() is called and initial value not set",
				m.getSubstring(1, 10)
						.equals("ERROR IN INPUT (getSubString()), possibly not initialized"));
	}

	@Test
	// This test ensures that the first char is returned when the user gives 1,1
	// as an input.
	// Keep in mind that 1 indicates the position of the first char.
	public void getSubstring_InitedString_ShouldReturnFirstCharWhenGivenOnlyFirstRange() {

		MyString m = new MyString();
		m.setString("TEST");
		assertTrue("First char not returned as expected", m.getSubstring(1, 1)
				.equals("T"));
	}

	@Test
	// This test ensures that the last char is returned when the user gives the
	// only the last indices as inputs.
	// Keep in mind that 1 indicates the position of the first char.
	public void getSubstring_InitedString_ShouldReturnLastCharWhenGivenOnlyFirstRange() {

		MyString m = new MyString();
		m.setString("TESTa");
		assertTrue("Last char not returned as expected",
				m.getSubstring(m.getString().length(), m.getString().length())
						.equals("a"));
	}

	@Test
	// This test ensures that the correct substring is returned when the user
	// gives inputs that are inside of the string.
	// Keep in mind that 1 indicates the position of the first char.
	// Also the indices passed by the user should be inclusive.
	public void getSubstring_InitedString_ShouldReturnContents() {

		MyString m = new MyString();
		m.setString("TESTa");
		assertTrue("Substring not returned as expected", m.getSubstring(2, 4)
				.equals("EST"));
	}

	@Test
	// This test ensures that 0 is returned when the string has not been
	// initialized.
	// Keep in mind that 1 indicates the position of the first char.
	public void indexOf_UninitedString_ShouldReturnZero() {

		MyString m = new MyString();
		assertTrue(
				"0 should be returned if string is not initalized or if the char is not in the string",
				m.indexOf('a') == 0);
	}

	@Test
	// This test ensures that 1 is returned when the string has been initialized
	// and the char given is first in the string.
	// Keep in mind that 1 indicates the position of the first char.
	public void indexOf_InitedString_ShouldReturnOne() {

		MyString m = new MyString();
		m.setString("TEST");
		assertTrue("1 should be returned", m.indexOf('T') == 1);
	}

	@Test
	// This test ensures that the string size is returned when the string has
	// been initialized and the char given is last in the string.
	// Keep in mind that 1 indicates the position of the first char.
	public void indexOf_InitedString_ShouldReturnLength() {

		MyString m = new MyString();
		m.setString("TESTa");
		assertTrue("String length should be returned", m.indexOf('a') == m
				.getString().length());
	}

	@Test
	// This test ensures that the proper number is returned when the string has
	// been initialized and the char given is within the string.
	// Keep in mind that 1 indicates the position of the first char.
	public void indexOf_InitedString_ShouldReturnPos() {

		MyString m = new MyString();
		m.setString("TESTa");
		assertTrue("Proper position should be returned", m.indexOf('S') == 3);
	}

	@Test
	// This test ensures that an empty string is returned if the string has not
	// been initialized and removeChar() is called.
	public void removeChar_UninitedString_ShouldReturnEmptyString() {

		MyString m = new MyString();
		m.removeChar('T');
		assertTrue("Empty string should be returned", m.getString().equals(""));
	}

	@Test
	// This test ensures that the original string is returned if the string has
	// been initialized and removeChar() is called and the given char is not
	// present in the string.
	public void removeChar_InitedString_ShouldReturnOrigStringIfPassedCharNotPresent() {

		MyString m = new MyString();
		m.setString("TEST");
		m.removeChar('X');
		assertTrue("Original string should be returned",
				m.getString().equals("TEST"));
	}

	@Test
	// This test ensures that the proper string is returned if the string has
	// been initialized and removeChar() is called and the given char is
	// present in the first position of the string.
	public void removeChar_InitedString_ShouldReturnStringIfPassedCharPresentInBeginning() {

		MyString m = new MyString();
		m.setString("aTEST");
		m.removeChar('a');
		assertTrue("Removal of first char not correct",
				m.getString().equals("TEST"));
	}

	@Test
	// This test ensures that the proper string is returned if the string has
	// been initialized and removeChar() is called and the given char is
	// present in the last position of the string.
	public void removeChar_InitedString_ShouldReturnStringIfPassedCharPresentInEnd() {

		MyString m = new MyString();
		m.setString("TESTa");
		m.removeChar('a');
		assertTrue("Removal of last char not correct",
				m.getString().equals("TEST"));
	}

	@Test
	// This test ensures that the proper string is returned if the string has
	// been initialized and removeChar() is called and the given char is
	// present in the middle of the string.
	public void removeChar_InitedString_ShouldReturnStringIfPassedCharPresentInMiddle() {

		MyString m = new MyString();
		m.setString("TEaST");
		m.removeChar('a');
		assertTrue("Removal of middle char not correct",
				m.getString().equals("TEST"));
	}

	@Test
	// This test ensures that an empty string is returned if the string has
	// not been initialized and invert() has been called.
	public void invert_UninitedString_ShouldReturnEmptyString() {

		MyString m = new MyString();

		m.invert();
		assertTrue("Empty string should be returned", m.getString().equals(""));
	}

	@Test
	// This test ensures that the original string is returned if the string has
	// been initialized and invert() has been called and the string is only one
	// char long.
	public void invert_InitedString_ShouldReturnChar() {

		MyString m = new MyString();
		m.setString("T");
		m.invert();
		assertTrue("Original string should be returned",
				m.getString().equals("T"));
	}

	@Test
	// This test ensures that the original string is returned inverted if the
	// string has
	// been initialized and invert() has been called and the string is only two
	// chars long.
	public void invert_InitedString_ShouldReturnTwoFlipped() {

		MyString m = new MyString();
		m.setString("TE");
		m.invert();
		assertTrue("Inversion not commited properly", m.getString()
				.equals("ET"));
	}

	@Test
	// This test ensures that the original string is returned inverted if the
	// string has
	// been initialized and invert() has been called and the string is only 3+
	// chars long.
	public void invert_InitedString_ShouldReturnInverted() {

		MyString m = new MyString();
		m.setString("TEST");
		m.invert();
		assertTrue("Inversion not commited properly",
				m.getString().equals("TSET"));
	}

}
