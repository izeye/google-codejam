package com.izeye.codejam.codejam2009.qr.c;

import com.izeye.codejam.common.AbstractProblemSolver;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by izeye on 15. 3. 18..
 */
public class CodeJam2009QrCProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/codejam2009/qr/c/";
	private static final String SMALL_INPUT_FILENAME = "C-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "C-large-practice.in";

	private static final String SENTENCE = "welcome to code jam";

	public CodeJam2009QrCProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		String line = br.readLine();
		int occurrences = getOccurrences(line, 0, line.length() - SENTENCE.length(), 0);
		String result = String.format("%04d", occurrences);
		return result.substring(result.length() - 4);
	}

	// FIXME:
	// This algorithm's complexity is `n!`.
	// So it's too naive to solve the large input.
	private int getOccurrences(
			String line, int startIndex, int endIndex, int sentenceIndex) {
		char charInSentence = SENTENCE.charAt(sentenceIndex);

		int occurrences = 0;
		for (int i = startIndex; i <= endIndex; i++) {
			char c = line.charAt(i);
			if (c == charInSentence) {
				if (sentenceIndex == SENTENCE.length() - 1) {
					occurrences++;
				} else {
					occurrences += getOccurrences(
							line, i + 1, endIndex + 1, sentenceIndex + 1);
				}
			}
		}
		return occurrences;
	}

}
