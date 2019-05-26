package com.app.library.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

	@Test
	public void shouldMatches() {
		String t1 = "text";
		String t2 = "text";
		String t3 = "text";

		boolean result = StringUtils.matches(t1, t2, t3);
		assertTrue("Texts should be matched", result);
	}

	@Test
	public void shouldNotMatches() {
		String t1 = "text";
		String t2 = null;
		String t3 = "text";

		boolean result = StringUtils.matches(t1, t2, t3);
		assertFalse("Texts should be not matched - one of them is null", result);
	}

	@Test
	public void shouldNotMatchesWithEmptyList() {
		boolean result = StringUtils.matches();
		assertFalse("Should be not matched - empty list", result);
	}
}