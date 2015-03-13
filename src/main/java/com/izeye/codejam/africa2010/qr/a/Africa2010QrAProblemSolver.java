package com.izeye.codejam.africa2010.qr.a;

import com.izeye.codejam.common.AbstractProblemSolver;

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
public class Africa2010QrAProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/africa2010/qr/a/";
	private static final String SMALL_INPUT_FILENAME = "A-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "A-large-practice.in";

	public Africa2010QrAProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
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
		return String.format("%d %d", i + 1, j + 1);
	}

}
