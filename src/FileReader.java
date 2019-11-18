import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileReader {
	
	
	/**
	 * Reads a file and put each word into the data structure
	 * 
	 * @param inputFile the input file to read
	 * @param words     data structure to store all the words in the input file
	 * @throws IOException if unable to read the file
	 * 
	 */
	public static void readFile(Path inputFile, ArrayList<String> words) throws IOException {
		try (BufferedReader br = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
			String line;
			String name = inputFile.toString();
			while ((line = br.readLine()) != null) {
					words.add(line);
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		String inputFile = "";
		
		
		
	}

}
