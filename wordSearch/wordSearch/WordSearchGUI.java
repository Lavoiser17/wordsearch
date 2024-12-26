package wordSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A Word-Search-Helper - GUI Interface
 * 2024-12-16
 */
public class WordSearchGUI {

	static String startPath = "../wordsearch/src/wordsearch/examples";
	static int mainFrameX=500;
	static int mainFrameY=600;
	
	public static void main(String[] args) {

		MapHandler map = new MapHandler();
		String mapFile = MapFile.open("text map", "txt", startPath);
		if (mapFile == null) {
			System.exit(0);
		}
		map.loadMap(mapFile);
		char[][] letters = map.clone();

		// Main Frame
		JFrame frame = new JFrame("WORDSEARCH HELPER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(mainFrameX, mainFrameY);

		// Panel Display the wordsearch puzzle
		MapPanel letterPanel = new MapPanel(letters);

		// Elements
		JPanel inputPanel = new JPanel(new FlowLayout());
		JTextField inputField = new JTextField(20);
		JButton okButton = new JButton("OK");
		JButton endButton = new JButton("End");

		// Action for OK and InputField
		ActionListener performAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = inputField.getText();
				if (input.length() < 2) {
					JOptionPane.showMessageDialog(frame, "Too short!");
				} else {
					input = input.toUpperCase();
					List<int[]> result = map.findWord(input);
					System.out.println(result.size());

					if (result.size() == 0) {

						JOptionPane.showMessageDialog(frame, "No match!");
						letterPanel.repaint();

					} else {
						letterPanel.saveResults(result);
						inputField.setText("");
						letterPanel.repaint();
					}
				}
			}
		};
		endButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		okButton.addActionListener(performAction);
		inputField.addActionListener(performAction);

		inputPanel.add(new JLabel("Word:"));
		inputPanel.add(inputField);
		inputPanel.add(okButton);
		inputPanel.add(endButton);

		frame.add(new JScrollPane(letterPanel), BorderLayout.CENTER);
		frame.add(inputPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}
}
