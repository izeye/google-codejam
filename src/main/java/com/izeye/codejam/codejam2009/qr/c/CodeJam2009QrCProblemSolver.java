package com.izeye.codejam.codejam2009.qr.c;

import com.izeye.codejam.common.AbstractProblemSolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by izeye on 15. 3. 18..
 */
public class CodeJam2009QrCProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/codejam2009/qr/c/";
	private static final String SMALL_INPUT_FILENAME = "C-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "C-large-practice.in";

	private static final String SENTENCE = "welcome to code jam";
	private static final int SENTENCE_LENGTH = SENTENCE.length();

	private BigInteger[][][] cache;

	public CodeJam2009QrCProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		String line = br.readLine();
		int lineLength = line.length();

		this.cache = new BigInteger[lineLength][lineLength][SENTENCE_LENGTH];

		BigInteger occurrences = getOccurrences(line, 0, lineLength - SENTENCE_LENGTH, 0);
		String result = "000" + occurrences.toString();
		return result.substring(result.length() - 4);
	}

	private BigInteger getOccurrences(
			String line, int startIndex, int endIndex, int sentenceIndex) {
		if (endIndex < 0) {
			return BigInteger.ZERO;
		}

		BigInteger cached = cache[startIndex][endIndex][sentenceIndex];
		if (cached != null) {
			return cached;
		}

		char charInSentence = SENTENCE.charAt(sentenceIndex);

		BigInteger occurrences = BigInteger.ZERO;
		for (int i = startIndex; i <= endIndex; i++) {
			char c = line.charAt(i);
			if (c == charInSentence) {
				if (sentenceIndex == SENTENCE.length() - 1) {
					occurrences = occurrences.add(BigInteger.ONE);
				} else {
					occurrences = occurrences.add(
							getOccurrences(line, i + 1, endIndex + 1, sentenceIndex + 1));
				}
			}
		}

		cache[startIndex][endIndex][sentenceIndex] = occurrences;
		return occurrences;
	}

}
