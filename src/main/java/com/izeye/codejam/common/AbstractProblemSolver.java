package com.izeye.codejam.common;

import com.izeye.codejam.util.StopWatch;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by izeye on 15. 3. 13..
 */
public abstract class AbstractProblemSolver implements ProblemSolver {

	private final String directory;
	private final String smallInputFilename;
	private final String largeInputFilename;

	protected AbstractProblemSolver(String directory, String smallInputFilename, String largeInputFilename) {
		this.directory = directory;
		this.smallInputFilename = smallInputFilename;
		this.largeInputFilename = largeInputFilename;
	}

	@Override
	public String getDirectory() {
		return directory;
	}

	@Override
	public String getSmallInputFilename() {
		return smallInputFilename;
	}

	@Override
	public String getLargeInputFilename() {
		return largeInputFilename;
	}

	@Override
	public void solve(String inputFilename, String outputFilename) throws IOException {
		String output = solve(inputFilename);
		FileWriter fw = new FileWriter(outputFilename);
		IOUtils.write(output, fw);
		fw.close();
	}

	@Override
	public String solve(String inputFilename) throws IOException {
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

	protected String solveTestCases(BufferedReader br) throws IOException {
		StringBuilder sbOutput = new StringBuilder();
		int testCaseCount = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseCount; testCase++) {
			sbOutput.append(String.format("Case #%d: %s\n", testCase, solveTestCase(br)));
		}
		return sbOutput.toString().trim();
	}

	protected abstract String solveTestCase(BufferedReader br) throws IOException;

}
