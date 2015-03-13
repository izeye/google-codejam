package com.izeye.codejam.util;

/**
 * Created by izeye on 15. 3. 13..
 */
public class ArrayUtils {

	public static <T> T[] reverse(T[] array) {
		T temp;
		for (int i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
		return array;
	}

}
