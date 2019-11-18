import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileReaderAndWriter {

	static ArrayList<String> wordsInfile = new ArrayList<String>();

	/**
	 * Reads a file and put each word into the data structure
	 * 
	 * @param inputFile the input file to read
	 * @param words     data structure to store all the words in the input file
	 * @return
	 * @throws IOException if unable to read the file
	 * 
	 */
	public static ArrayList<String> readFile(Path inputFile) throws IOException {

		try (BufferedReader br = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
			String line;
			String name = inputFile.toString();
			while ((line = br.readLine()) != null) {
				wordsInfile.add(line);
			}
		}

		return wordsInfile;
	}

	/**
	 * Writes the elements to file.
	 *
	 * @param elements the elements to write
	 * @param path     the file path to use
	 * @throws IOException
	 */
	public static void writeFile(Path outputFile) throws IOException {
		
		
		try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {

			int size = wordsInfile.size();
			for (int i = 0; i < size; i++) {
				String str = wordsInfile.get(i);
				writer.write(str);
				writer.write("\n");
//				if (i < size - 1) {
//					writer.write("\n");
//				}
//				writer.close();

			}
		}

	}

	public static void printArrayList() {

		for (String word : wordsInfile) {

			System.out.println(word);
		}
	}

	public static void main(String[] args) {

		printArrayList();

	}

}
