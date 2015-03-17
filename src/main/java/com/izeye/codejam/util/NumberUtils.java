package com.izeye.codejam.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by izeye on 15. 3. 14..
 */
public class NumberUtils {

	public static char digit2Char(int digit) {
		return (char) (digit + '0');
	}

	public static SearchResult searchMin(int... numbers) {
		int minIndex = 0;
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < numbers.length; i++) {
			int number = numbers[i];
			if (number < minValue) {
				minValue = number;
				minIndex = i;
			}
		}
		return new SearchResult(minIndex, minValue);
	}

	@Data
	@AllArgsConstructor
	public static class SearchResult {
		private int index;
		private int value;
	}

}
