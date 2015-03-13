package com.izeye.codejam;

import com.izeye.codejam.africa2010.qr.a.Africa2010QrAProblemSolver;
import com.izeye.codejam.africa2010.qr.b.Africa2010QrBProblemSolver;
import com.izeye.codejam.africa2010.qr.c.Africa2010QrCProblemSolver;
import com.izeye.codejam.common.ProblemSolver;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 3. 13..
 */
public class ProblemSolverRunner {

//	static ProblemSolver problemSolver = new Africa2010QrAProblemSolver();
//	static ProblemSolver problemSolver = new Africa2010QrBProblemSolver();
	static ProblemSolver problemSolver = new Africa2010QrCProblemSolver();

	static String sampleDirectory;
	static String inputDirectory;
	static String outputDirectory;
	static String sampleInputFilename;
	static String sampleOutputFilename;
	static String smallInputFilename;
	static String largeInputFilename;
	static String smallOutputFilename;
	static String largeOutputFilename;

	@BeforeClass
	public static void setUpOnce() {
		String directory = problemSolver.getDirectory();
		sampleDirectory = directory + "sample/";
		inputDirectory = directory + "input/";
		outputDirectory = directory + "output/";
		sampleInputFilename = sampleDirectory + "input.txt";
		sampleOutputFilename = sampleDirectory + "output.txt";
		smallInputFilename = inputDirectory + problemSolver.getSmallInputFilename();
		smallOutputFilename = outputDirectory + "small.out";
		largeInputFilename = inputDirectory + problemSolver.getLargeInputFilename();
		largeOutputFilename = outputDirectory + "large.out";
	}

	@Test
	public void solveSample() throws IOException {
		String output = problemSolver.solve(sampleInputFilename);
		String expectedOutput = IOUtils.toString(new FileReader(sampleOutputFilename)).trim();
		assertThat(output, is(expectedOutput));
	}

	@Test
	public void solveSmallInput() throws IOException {
		problemSolver.solve(smallInputFilename, smallOutputFilename);
	}

	@Test
	public void solveLargeInput() throws IOException {
		problemSolver.solve(largeInputFilename, largeOutputFilename);
	}

}
