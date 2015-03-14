package com.izeye.codejam.codejam2009.qr.a;

import com.izeye.codejam.common.AbstractProblemSolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by izeye on 15. 3. 14..
 */
public class CodeJam2009QrAProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/codejam2009/qr/a/";
	private static final String SMALL_INPUT_FILENAME = "A-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "A-large-practice.in";

	private Set<String> words = new HashSet<>();

	public CodeJam2009QrAProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCases(BufferedReader br) throws IOException {
		StringBuilder sbOutput = new StringBuilder();
		String line = br.readLine();
		String[] values = line.split(" ");
		int wordLength = Integer.parseInt(values[0]);
		int wordCount = Integer.parseInt(values[1]);
		int testCaseCount = Integer.parseInt(values[2]);
		for (int i = 0; i < wordCount; i++) {
			words.add(br.readLine());
		}
		for (int testCase = 1; testCase <= testCaseCount; testCase++) {
			sbOutput.append(String.format("Case #%d: %s\n", testCase, solveTestCase(br)));
		}
		return sbOutput.toString().trim();
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		int matchedCount = 0;
		String pattern = br.readLine();
		String regex = pattern.replace('(', '[').replace(')', ']');
		Pattern regexPattern = Pattern.compile(regex);
		Iterator<String> it = words.iterator();
		while (it.hasNext()) {
			String word = it.next();
			if (regexPattern.matcher(word).matches()) {
				matchedCount++;
			}
		}
		return String.valueOf(matchedCount);
	}

}
