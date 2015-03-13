package com.izeye.codejam.common;

import java.io.IOException;

/**
 * Created by izeye on 15. 3. 13..
 */
public interface ProblemSolver {

	String getDirectory();

	String getSmallInputFilename();

	String getLargeInputFilename();

	void solve(String inputFilename, String outputFilename) throws IOException;

	String solve(String inputFilename) throws IOException;

}
