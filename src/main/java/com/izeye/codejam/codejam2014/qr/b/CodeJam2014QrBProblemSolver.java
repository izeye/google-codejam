package com.izeye.codejam.codejam2014.qr.b;

import com.izeye.codejam.common.AbstractProblemSolver;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by izeye on 15. 3. 22..
 */
public class CodeJam2014QrBProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/codejam2014/qr/b/";
	private static final String SMALL_INPUT_FILENAME = "B-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "B-large-practice.in";

	private static final int DEFAULT_COOKIES_PER_SECOND = 2;

	public CodeJam2014QrBProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		String[] values = br.readLine().split(" ");
		double farmPrice = Double.parseDouble(values[0]);
		double cookiesPerSecondByFarm = Double.parseDouble(values[1]);
		double targetCookieCount = Double.parseDouble(values[2]);

		double minSeconds = targetCookieCount / DEFAULT_COOKIES_PER_SECOND;
		for (int farmCount = 1; ; farmCount++) {
			double seconds = 0;
			for (int i = 0; i < farmCount; i++) {
				seconds += farmPrice / (cookiesPerSecondByFarm * i + DEFAULT_COOKIES_PER_SECOND);
			}
			seconds += targetCookieCount / (cookiesPerSecondByFarm * farmCount + DEFAULT_COOKIES_PER_SECOND);
			if (seconds > minSeconds) {
				break;
			}
			minSeconds = seconds;
		}
		return String.format("%.7f", minSeconds);
	}

}
