package com.izeye.codejam.codejam2014.qr.a;

import com.izeye.codejam.common.AbstractProblemSolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by izeye on 15. 3. 22..
 */
public class CodeJam2014QrAProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/codejam2014/qr/a/";
	private static final String SMALL_INPUT_FILENAME = "A-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = null;

	private static final String MULTIPLE_CARDS = "Bad magician!";
	private static final String NO_CARDS = "Volunteer cheated!";

	public CodeJam2014QrAProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		Set<Integer> numbersInFirstSelectedRow = getNumbersInSelectedRow(br);
		Set<Integer> numbersInSecondSelectedRow = getNumbersInSelectedRow(br);
		Set<Integer> numbers = new HashSet<>(numbersInFirstSelectedRow);
		numbers.retainAll(numbersInSecondSelectedRow);
		switch (numbers.size()) {
			case 0:
				return NO_CARDS;

			case 1:
				return numbers.iterator().next().toString();

			default:
				return MULTIPLE_CARDS;
		}
	}

	private Set<Integer> getNumbersInSelectedRow(BufferedReader br) throws IOException {
		Set<Integer> numbersInSelectedRow = new HashSet<>();
		Integer selectedRow = Integer.valueOf(br.readLine());
		for (int row = 1; row <= 4; row++) {
			String line = br.readLine();
			if (row == selectedRow) {
				for (String number : line.split(" ")) {
					numbersInSelectedRow.add(Integer.valueOf(number));
				}
			}
		}
		return numbersInSelectedRow;
	}

}
