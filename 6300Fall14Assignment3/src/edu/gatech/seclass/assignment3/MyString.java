package edu.gatech.seclass.assignment3;

public class MyString {

	private String str = "";

	public MyString() {

	}

	public MyString(String inStr) {

		this.str = inStr;
	}

	// Sets the value of the current string.
	public void setString(String str) {

		this.str = str;
	}

	// Returns the current string.
	public String getString() {

		return this.str;
	}

	// Returns a string that consists of all and only the vowels in the current
	// string.
	// Only letters a, e, i, o, and u (both lower and upper case) are considered
	// vowels.
	// The returned string contains each occurrence of a vowel in the current
	// string.
	public String getVowels() {

		String tmp = "";

		for (int i = 0; i < this.str.length(); i++) {
			if ("aeiou".indexOf(this.str.charAt(i)) > -1
					|| "AEIOU".indexOf(this.str.charAt(i)) > -1) {
				tmp += this.str.charAt(i) + "";
			}
		}

		return tmp;
	}

	// Returns a string that consists of the substring between start and end
	// indexes (both included) in the current string.
	// Index 1 corresponds to the first character in the current string.
	public String getSubstring(int start, int end) {

		try {

			return this.str.substring(start - 1, end);
		} catch (StringIndexOutOfBoundsException e) {

			return "ERROR IN INPUT (getSubString()), possibly not initialized";
		}

	}

	// Returns the index of the first occurrence of a character in the current
	// string.
	// Index 1 corresponds to the first character in the current string.
	public int indexOf(char c) {

		return this.str.indexOf(c) + 1;
	}

	// Removes all occurrences of the specified character from the current
	// string.
	public void removeChar(char c) {

		for (int i = 0; i < this.str.length(); i++) {
			if (this.str.charAt(i) == c) {
				if (i == 0) {

					this.str = this.str.substring(1);
				} else if (i == this.str.length() - 1) {
					this.str = this.str.substring(0, this.str.length() - 1);
				} else {
					this.str = this.str.substring(0, i)
							+ this.str.substring(i + 1, this.str.length());
				}
			}
		}

	}

	// Invert the current string.
	public void invert() {

		String tmp = "";
		for (int i = this.str.length() - 1; i > -1; i--) {
			tmp += this.str.charAt(i) + "";
		}
		this.str = tmp;
	}

}