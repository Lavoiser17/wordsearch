package wordSearch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Displaying the Word-Search Puzzel
 */

class MapPanel extends JPanel {
	// default view
	private int startX = 20;
	private int startY = 30;
	private int offsetX = 30;
	private int offsetY = 30;
	private int fontSize = 20;
	private int panelSizeX = 500;
	private int panelSizeY = 500;

	private static final long serialVersionUID = 2412204L;
	private final char[][] letters;

	private List<int[]> wordsCoordinates = new ArrayList<>();

	public MapPanel(char[][] letters) {
		this.letters = letters;
		this.setPreferredSize(new Dimension(panelSizeX, panelSizeY));
		// simple resizing
		if (Math.max(this.letters.length, this.letters[0].length) > 20) {
			offsetX = offsetX / 2;
			offsetY = offsetY / 2;
			fontSize = fontSize / 2;
		}
	}

	public void saveResults(List<int[]> words) {
		this.wordsCoordinates.addAll(words);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));

		// Draw Letters
		for (int row = 0; row < letters.length; row++) {
			for (int column = 0; column < letters[row].length; column++) {
				g.drawString(String.valueOf(letters[row][column]), startX + column * offsetX, startY + row * offsetY);
			}
		}
		// Draw lines for matched words
		for (int[] word : wordsCoordinates) {
			// Be Careful! X=Columns, Y=Rows
			g.setColor(Color.MAGENTA);
			int x1 = startX + fontSize / 3 + offsetX * word[1];
			int y1 = startY - fontSize / 3 + offsetY * word[0];

			int x2 = startX + fontSize / 3 + offsetX * word[3];
			int y2 = startY - fontSize / 3 + offsetY * word[2];

			g.drawLine(x1, y1, x2, y2);
		}
	}
}