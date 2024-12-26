package wordSearch;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Load the txt-file with the Puzzle in a string-List, with a file chooser
 * Class with multiple purpose
 */
public final class MapFile {
	public static String open(String fileExtensionDescription, String fileExtension, String startPath) {
		JFileChooser fileChooser = new JFileChooser();

		if (startPath != null && !startPath.isEmpty()) {
			fileChooser.setCurrentDirectory(new File(startPath));
		}
		fileChooser.setDialogTitle("Wählen Sie eine Datei aus");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(fileExtensionDescription, fileExtension);
		fileChooser.setFileFilter(filter);
		int userSelection = fileChooser.showOpenDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
		} else {
			return null;
		}
	}

	public static List<String> loadLines(String inputFile) {
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Path.of(inputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}