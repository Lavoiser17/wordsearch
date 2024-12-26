package wordSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Search a word in Word-Search Puzzle find the word x-times and returns a list
 * with the coordinates of the words founded in the Puzzle: Start-Coordinates -
 * End-Coordinates.
 * 2024-12-16
 */

public class MapHandler {

	private static char[][] wordSearch;
	private static int rows;
	private static int columns;
	// {delta row, delta column }
	private static final int[][] DIRECTION = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 },
			{ -1, 0 }, { -1, 1 } };

	public void loadMap(String inputFile) {
		List<String> lines = MapFile.loadLines(inputFile);

		for (int i = 0; i < lines.size(); i++) {
			String s = lines.get(i).replace(" ", "");
			s = s.toUpperCase();
			lines.set(i, s);
		}
		// Array
		// it is a 2D array, the length of the first row determines the size
		columns = lines.get(0).length();
		rows = lines.size();
		char[][] map = new char[rows][columns];

		// too longs rows will be cut
		// too short rows will be filled with 'X'

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (column < lines.get(row).length()) {
					map[row][column] = lines.get(row).charAt(column);
				} else {
					map[row][column] = 'X';
				}
			}
		}
		wordSearch = map;
	}

	public List<int[]> findWord(String word) {
		// returns a list will the Coordinates of the word(s) begin and end of the word
		List<int[]> results = new ArrayList<int[]>();

		// At least 2 letters long and not to long
		if (word.length() < 2 || word.length() > Math.max(rows, columns)) {
			return results;
		}

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++)

				if (wordSearch[row][column] == word.charAt(0)) {

					for (int dir = 0; dir < DIRECTION.length; dir++) {
						// check the next letter
						if (findWordRecurs(row + DIRECTION[dir][0], column + DIRECTION[dir][1], word, 1, dir)) {
							int[] resultCoordinates = { row, column, row + (word.length() - 1) * DIRECTION[dir][0],
									column + (word.length() - 1) * DIRECTION[dir][1] };
							results.add(resultCoordinates);
						}
					}
				}
		}
		return results;
	}

	static private boolean findWordRecurs(int r, int c, String word, int stack, int dir) {
		boolean result = false;
		if (!isInSideMap(r, c)) {
		} else if (wordSearch[r][c] != word.charAt(stack)) {
			result = false;
		} else if (stack == word.length() - 1) {
			result = true;
		} else {
			stack++;
			// RECURSIV
			result = findWordRecurs(r + DIRECTION[dir][0], c + DIRECTION[dir][1], word, stack, dir);
		}
		return result;
	}

	private static boolean isInSideMap(int row, int column) {
		return (row >= 0 && column >= 0 && row < rows && column < columns);
	}

	@Override
	public char[][] clone() {
		char[][] wordsearchCopy = new char[rows][columns];
		// deepcopy
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				wordsearchCopy[row][column] = wordSearch[row][column];
			}
		}
		return wordsearchCopy;
	}
}
