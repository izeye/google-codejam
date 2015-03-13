package com.izeye.codejam.africa2010.qr.b;

import com.izeye.codejam.common.AbstractProblemSolver;
import com.sun.deploy.util.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by izeye on 15. 3. 13..
 */
public class Africa2010QrBProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/africa2010/qr/b/";
	private static final String SMALL_INPUT_FILENAME = "B-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "B-large-practice.in";

	public Africa2010QrBProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		String[] words = br.readLine().split(" ");
		ArrayUtils.reverse(words);
		return StringUtils.join(Arrays.asList(words), " ");
	}

}
