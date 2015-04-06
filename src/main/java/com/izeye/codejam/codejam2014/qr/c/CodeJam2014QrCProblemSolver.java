package com.izeye.codejam.codejam2014.qr.c;

import com.izeye.codejam.common.AbstractProblemSolver;
import com.izeye.codejam.util.ArrayUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by izeye on 15. 3. 22..
 */
public class CodeJam2014QrCProblemSolver extends AbstractProblemSolver {

	private static final String DIRECTORY = "src/main/resources/codejam2014/qr/c/";
	private static final String SMALL_INPUT_FILENAME = "C-small-practice.in";
	private static final String LARGE_INPUT_FILENAME = "C-large-practice.in";
	
	private static final String NO_CONFIGURATION = "Impossible";
	
	static final char MINE = '*';
	static final char EMPTY = '.';
	static final char CLICK = 'c';

	public CodeJam2014QrCProblemSolver() {
		super(DIRECTORY, SMALL_INPUT_FILENAME, LARGE_INPUT_FILENAME);
	}

	@Override
	protected String solveTestCase(BufferedReader br) throws IOException {
		String[] values = br.readLine().split(" ");
		int rowCount = Integer.parseInt(values[0]);
		int columnCount = Integer.parseInt(values[1]);
		int numberOfMines = Integer.parseInt(values[2]);

		return doSolve(rowCount, columnCount, numberOfMines);
	}

	String doSolve(int rowCount, int columnCount, int numberOfMines) {
		Character[][] configuration = getAnyConfiguration(rowCount, columnCount, numberOfMines);
		return configuration != null ? ArrayUtils.toString(configuration) : NO_CONFIGURATION;
	}

	private Character[][] getAnyConfiguration(int rowCount, int columnCount, int numberOfMines) {
		int numberOfCells = rowCount * columnCount;
		int numberOfNonMineCells = numberOfCells - numberOfMines;

		return getAnyConfiguration(
				new int[numberOfMines], 0, numberOfNonMineCells, 0, rowCount, columnCount);
	}

	// FIXME:
	// This algorithm is based on brute-force search.
	// It's too naive to solve the large input.
	private Character[][] getAnyConfiguration(
			int[] mineOffsets, int startOffset, int endOffset, int mineIndex, int rowCount, int columnCount) {
		if (mineIndex == mineOffsets.length) {
			Character[][] configuration = mineOffsets2Configuration(mineOffsets, rowCount, columnCount);
			return findOneClickConfiguration(configuration);
		}
		for (int i = startOffset; i <= endOffset; i++) {
			mineOffsets[mineIndex] = i;

			int[] copiedMineOffsets = new int[mineOffsets.length];
			System.arraycopy(mineOffsets, 0, copiedMineOffsets, 0, mineOffsets.length);

			Character[][] configuration = getAnyConfiguration(
					copiedMineOffsets, i + 1, endOffset + 1, mineIndex + 1, rowCount, columnCount);
			if (configuration != null) {
				return configuration;
			}
		}
		return null;
	}
	
	private Character[][] mineOffsets2Configuration(int[] mineOffsets, int rowCount, int columnCount) {
		Character[][] configuration = new Character[rowCount][columnCount];
		for (int mineOffset : mineOffsets) {
			configuration[mineOffset / columnCount][mineOffset % columnCount] = MINE;
		}
		return configuration;
	}
	
	private Character[][] findOneClickConfiguration(Character[][] configuration) {
		for (int row = 0; row < configuration.length; row++) {
			for (int column = 0; column < configuration[row].length; column++) {
				if (configuration[row][column] == null) {
					Character[][] copiedConfiguration = ArrayUtils.copy(configuration);
					copiedConfiguration[row][column] = CLICK;
					expand(copiedConfiguration, row, column);
					if (isSolved(copiedConfiguration)) {
						return copiedConfiguration;
					}
				}
			}
		}
		return null;
	}

	private void expand(Character[][] configuration, int row, int column) {
		Set<Cell> cellsToBeExpanded = new HashSet<>();

		if (row - 1 >= 0) {
			if (column - 1 >= 0) {
				if (!isExpandable(configuration, row - 1, column - 1, cellsToBeExpanded)) return;
			}
			if (!isExpandable(configuration, row - 1, column, cellsToBeExpanded)) return;
			if (column + 1 < configuration[row - 1].length) {
				if (!isExpandable(configuration, row - 1, column + 1, cellsToBeExpanded)) return;
			}
		}
		if (column - 1 >= 0) {
			if (!isExpandable(configuration, row, column - 1, cellsToBeExpanded)) return;
		}
		if (column + 1 < configuration[row].length) {
			if (!isExpandable(configuration, row, column + 1, cellsToBeExpanded)) return;
		}
		if (row + 1 < configuration.length) {
			if (column - 1 >= 0) {
				if (!isExpandable(configuration, row + 1, column - 1, cellsToBeExpanded)) return;
			}
			if (!isExpandable(configuration, row + 1, column, cellsToBeExpanded)) return;
			if (column + 1 < configuration[row + 1].length) {
				if (!isExpandable(configuration, row + 1, column + 1, cellsToBeExpanded)) return;
			}
		}

		Iterator<Cell> iterator = cellsToBeExpanded.iterator();
		while (iterator.hasNext()) {
			Cell cellToBeExpanded = iterator.next();
			int rowOfCellToBeExpanded = cellToBeExpanded.getRow();
			int columnOfCellToBeExpanded = cellToBeExpanded.getColumn();
			configuration[rowOfCellToBeExpanded][columnOfCellToBeExpanded] = EMPTY;
			expand(configuration, rowOfCellToBeExpanded, columnOfCellToBeExpanded);
		}
	}

	private boolean isExpandable(
			Character[][] configuration, int row, int column, Set<Cell> cellsToBeExpanded) {
		Character value = configuration[row][column];
		if (value != null && value == MINE) {
			return false;
		}
		
		// FIXME:
		// I did this to eliminate code duplication
		// but making side effects in getters is not good.
		if (value == null) {
			cellsToBeExpanded.add(new Cell(row, column));
		}
		return true;
	}
	
	private boolean isSolved(Character[][] configuration) {
		for (int row = 0; row < configuration.length; row++) {
			for (int column = 0; column < configuration[row].length; column++) {
				if (configuration[row][column] == null) {
					return false;
				}
			}
		}
		return true;
	}

	@Data
	@AllArgsConstructor
	class Cell {
		private int row;
		private int column;
	}

	@Override
	protected char getTestResultHeaderDelimiter() {
		return '\n';
	}

}
