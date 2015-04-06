package com.izeye.codejam.codejam2014.qr.c;

import org.junit.Test;

/**
 * Created by izeye on 15. 4. 6..
 */
public class CodeJam2014QrCProblemSolverTests {
	
	CodeJam2014QrCProblemSolver solver = new CodeJam2014QrCProblemSolver();
	
	@Test
	public void testDoSolve() {
		int rowCount = 50;
		int columnCount = 50;
		int numberOfMines = 2450;
		String configuration = solver.doSolve(rowCount, columnCount, numberOfMines);
		System.out.println(configuration);
	}
	
}
