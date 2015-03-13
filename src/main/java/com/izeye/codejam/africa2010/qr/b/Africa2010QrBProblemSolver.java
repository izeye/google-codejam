package com.izeye.codejam.africa2010.qr.b;

import com.izeye.codejam.common.AbstractProblemSolver;
import com.izeye.codejam.util.ArrayUtils;
import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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
		return StringUtils.join(Arrays.asList(ArrayUtils.reverse(words)), " ");
	}

}
