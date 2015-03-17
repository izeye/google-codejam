package com.izeye.codejam.util;

/**
 * Created by izeye on 15. 3. 15..
 */
public class ArrayUtils {

	public static void print(int[][] array) {
		System.out.println(toString(array));
	}

	public static String toString(int[][] array) {
		StringBuilder sb = new StringBuilder();
		for (int[] row : array) {
			for (int column : row) {
				sb.append(column);
				sb.append(' ');
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static <T> void print(T[][] array) {
		System.out.println(toString(array));
	}

	public static <T> String toString(T[][] array) {
		StringBuilder sb = new StringBuilder();
		for (T[] row : array) {
			for (T column : row) {
				sb.append(column);
				sb.append(' ');
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append('\n');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
