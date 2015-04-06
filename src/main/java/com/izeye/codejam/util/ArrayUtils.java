package com.izeye.codejam.util;

import org.apache.commons.lang3.SerializationUtils;

/**
 * Created by izeye on 15. 3. 15..
 */
public class ArrayUtils {

	public static String toString(int[][] array, Character columnDelimiter) {
		StringBuilder sb = new StringBuilder();
		for (int[] row : array) {
			for (int column : row) {
				sb.append(column);
				if (columnDelimiter != null) {
					sb.append(columnDelimiter);
				}
			}
			if (columnDelimiter != null) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static String toString(int[][] array) {
		return toString(array, null);
	}

	public static <T> String toString(T[][] array, Character columnDelimiter) {
		StringBuilder sb = new StringBuilder();
		for (T[] row : array) {
			for (T column : row) {
				sb.append(column);
				if (columnDelimiter != null) {
					sb.append(columnDelimiter);
				}
			}
			if (columnDelimiter != null) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static <T> String toString(T[][] array) {
		return toString(array, null);
	}
	
	public static <T> T[][] copy(T[][] src) {
		return SerializationUtils.clone(src);
	}

}
