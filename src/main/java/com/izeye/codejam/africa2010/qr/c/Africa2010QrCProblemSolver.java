package com.izeye.codejam.africa2010.qr.c;

import com.izeye.codejam.common.AbstractProblemSolver;
import com.izeye.codejam.util.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by izeye on 15. 3. 13..
 */
public class Africa2010QrCProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/africa2010/qr/c/";
	private static final String SMALL_INPUT_FILENAME = "C-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "C-large-practice.in";

	public Africa2010QrCProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		return line2KeyPresses(br.readLine());
	}

	private Map<Integer, String> number2Chars = new HashMap<>();
	private Map<Character, String> char2KeyPresses = new HashMap<>();
	{
		number2Chars.put(2, "abc");
		number2Chars.put(3, "def");
		number2Chars.put(4, "ghi");
		number2Chars.put(5, "jkl");
		number2Chars.put(6, "mno");
		number2Chars.put(7, "pqrs");
		number2Chars.put(8, "tuv");
		number2Chars.put(9, "wxyz");
		number2Chars.put(0, " ");
		for (Map.Entry<Integer, String> entry : number2Chars.entrySet()) {
			Integer number = entry.getKey();
			String characters = entry.getValue();
			for (int i = 0; i < characters.length(); i++) {
				char c = characters.charAt(i);
				char2KeyPresses.put(c, StringUtils.repeat(NumberUtils.int2Char(number), i + 1));
			}
		}
	}

	private String line2KeyPresses(String line) {
		StringBuilder sbKeyPresses = new StringBuilder();
		String previousKeyPresses = null;
		for (char c : line.toCharArray()) {
			String keyPresses = char2KeyPresses.get(c);
			if (previousKeyPresses != null
					&& keyPresses.charAt(0) == previousKeyPresses.charAt(0)) {
				sbKeyPresses.append(' ');
			}
			sbKeyPresses.append(keyPresses);
			previousKeyPresses = keyPresses;
		}
		return sbKeyPresses.toString();
	}

}
