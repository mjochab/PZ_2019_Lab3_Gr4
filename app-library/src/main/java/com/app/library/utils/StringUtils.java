package com.app.library.utils;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Utils for operations on text
 */
public class StringUtils {

	/**
	 * method to compare text arguments
	 * @param fields - array with text arguments
	 * @return - boolean value: true when every arguments are equal
	 */
	public static boolean matches(String... fields) {
		if (fields == null || fields.length == 0) return false;

		String p1 = fields[0];
		return Stream.of(fields).allMatch(el -> el != null && Objects.equals(el, p1));
	}
}
