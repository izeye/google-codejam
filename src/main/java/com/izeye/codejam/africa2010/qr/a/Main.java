package com.izeye.codejam.africa2010.qr.a;

import com.izeye.codejam.util.StopWatch;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 3. 13..
 */
public class Main {

	private static final String DIRECTORY = "src/main/resources/africa2010/qr/a/";
	private static final String SAMPLE_DIRECTORY = DIRECTORY + "sample/";
	private static final String INPUT_DIRECTORY = DIRECTORY + "input/";
	private static final String OUTPUT_DIRECTORY = DIRECTORY + "output/";
	private static final String SAMPLE_INPUT_FILENAME = SAMPLE_DIRECTORY + "input.txt";
	private static final String SAMPLE_OUTPUT_FILENAME = SAMPLE_DIRECTORY + "output.txt";
	private static final String SMALL_INPUT_FILENAME = INPUT_DIRECTORY + "A-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = INPUT_DIRECTORY + "A-large-practice.in";
	private static final String SMALL_OUTPUT_FILENAME = OUTPUT_DIRECTORY + "small.out";
	private static final String LARGE_OUTPUT_FILENAME = OUTPUT_DIRECTORY + "large.out";

	@Test
	public void solveSample() throws IOException {
		String output = solve(SAMPLE_INPUT_FILENAME);
		String expectedOutput = IOUtils.toString(new FileReader(SAMPLE_OUTPUT_FILENAME)).trim();
		assertThat(output, is(expectedOutput));
	}

	@Test
	public void solveSmallInput() throws IOException {
		solveInput(SMALL_INPUT_FILENAME, SMALL_OUTPUT_FILENAME);
	}

	@Test
	public void solveLargeInput() throws IOException {
		solveInput(LARGE_INPUT_FILENAME, LARGE_OUTPUT_FILENAME);
	}

	private void solveInput(String inputFilename, String outputFilename) throws IOException {
		String output = solve(inputFilename);
		FileWriter fw = new FileWriter(outputFilename);
		IOUtils.write(output, fw);
		fw.close();
	}

	private String solve(String inputFilename) throws IOException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		BufferedReader br = new BufferedReader(new FileReader(inputFilename));
		String output = solveTestCases(br);
		br.close();

		stopWatch.stop();
		long elapsedTime = stopWatch.getElapsedTime();
		System.out.println("Elapsed time in minutes: " + TimeUnit.MILLISECONDS.toMinutes(elapsedTime));
		System.out.println("Elapsed time in ms: " + elapsedTime);

		return output;
	}

	private String solveTestCases(BufferedReader br) throws IOException {
		StringBuilder sbOutput = new StringBuilder();
		int testCaseCount = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseCount; testCase++) {
			sbOutput.append(solveTestCase(br, testCase));
		}
		return sbOutput.toString().trim();
	}

	private String solveTestCase(BufferedReader br, int testCase) throws IOException {
		int credit = Integer.parseInt(br.readLine());
		int itemCount = Integer.parseInt(br.readLine());
		String[] prices = br.readLine().split(" ");
		assertThat(prices.length, is(itemCount));
		List<Integer> priceList = Arrays.asList(prices).stream().mapToInt(Integer::valueOf)
				.boxed().collect(Collectors.toList());
		boolean found = false;
		int i = 0, j = i+ 1;
		for (; i < priceList.size() - 1; i++) {
			int price1Candidate = priceList.get(i);
			for (j = i + 1; j < priceList.size(); j++) {
				int price2Candidate = priceList.get(j);
				if (price1Candidate + price2Candidate == credit) {
					found = true;
					break;
				}
			}
			if (found) {
				break;
			}
		}
		return String.format("Case #%d: %d %d\n", testCase, i + 1, j + 1);
	}

}
