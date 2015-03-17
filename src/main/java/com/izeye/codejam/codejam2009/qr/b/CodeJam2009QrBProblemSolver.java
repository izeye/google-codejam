package com.izeye.codejam.codejam2009.qr.b;

import com.izeye.codejam.common.AbstractProblemSolver;
import com.izeye.codejam.util.ArrayUtils;
import com.izeye.codejam.util.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by izeye on 15. 3. 15..
 */
public class CodeJam2009QrBProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/codejam2009/qr/b/";
	private static final String SMALL_INPUT_FILENAME = "B-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "B-large-practice.in";

	public CodeJam2009QrBProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	private char label;

	private int[][] map;
	private int mapHeight;
	private int mapWidth;
	private Character[][] drainageBasinMap;

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		label = 'a';

		String[] mapSize = br.readLine().split(" ");
		mapHeight = Integer.parseInt(mapSize[0]);
		mapWidth = Integer.parseInt(mapSize[1]);
		map = new int[mapHeight][mapWidth];
		for (int i = 0; i < mapHeight; i++) {
			String[] altitudes = br.readLine().split(" ");
			for (int j = 0; j < mapWidth; j++) {
				map[i][j] = Integer.parseInt(altitudes[j]);
			}
		}
//		ArrayUtils.print(map);

		drainageBasinMap = new Character[mapHeight][mapWidth];
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				markAndGetLabel(i, j);
			}
		}
//		ArrayUtils.print(drainageBasinMap);
		return ArrayUtils.toString(drainageBasinMap);
	}

	private Character markAndGetLabel(int row, int column) {
		Character label = drainageBasinMap[row][column];
		if (label != null) {
			return label;
		}

		int altitude = map[row][column];
		int northAltitude = getAltitude(row - 1, column);
		int westAltitude = getAltitude(row, column - 1);
		int eastAltitude = getAltitude(row, column + 1);
		int southAltitude = getAltitude(row + 1, column);
		NumberUtils.SearchResult searchResult = NumberUtils.searchMin(
				northAltitude, westAltitude, eastAltitude, southAltitude);
		if (searchResult.getValue() < altitude) {
			switch (searchResult.getIndex()) {
				case NORTH:
					label = markAndGetLabel(row - 1, column);
					break;

				case WEST:
					label = markAndGetLabel(row, column - 1);
					break;

				case EAST:
					label = markAndGetLabel(row, column + 1);
					break;

				case SOUTH:
					label = markAndGetLabel(row + 1, column);
					break;
			}
		} else {
			label = this.label;
			this.label++;
		}

		// Mark the label.
		drainageBasinMap[row][column] = label;

		// And return the label.
		return label;
	}

	private int getAltitude(int row, int column) {
		if (row >= 0 && row < mapHeight && column >= 0 && column < mapWidth) {
			return map[row][column];
		}
		return Integer.MAX_VALUE;
	}

	protected String solveTestCases(BufferedReader br) throws IOException {
		StringBuilder sbOutput = new StringBuilder();
		int testCaseCount = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= testCaseCount; testCase++) {
			sbOutput.append(String.format("Case #%d:\n%s\n", testCase, solveTestCase(br)));
		}
		return sbOutput.toString().trim();
	}

	private static final int NORTH = 0;
	private static final int WEST = 1;
	private static final int EAST = 2;
	private static final int SOUTH = 3;

}
