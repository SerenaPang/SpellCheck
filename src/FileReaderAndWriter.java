import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReaderAndWriter {

	/**
	 * Reads a file and put each word into the data structure
	 * 
	 * @param inputFile the input file to read
	 * @return a {@link List} of {@link String} objects.
	 * @throws IOException if unable to read the file
	 * 
	 */
	public static List<String> readFile(Path inputFile) throws IOException {
		List<String> words = new ArrayList<String>();

		try (BufferedReader br = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
			String line;
			while ((line = br.readLine()) != null) {
				words.add(line);
			}
		}

		return words;
	}

	/**
	 * Writes the elements to file.
	 *
	 * @param elements the elements to write
	 * @param path     the file path to use
	 * @throws IOException
	 */
	public static void writeFile(Path outputFile, List<String> words) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
			int size = words.size();
			for (int i = 0; i < size; i++) {
				String str = words.get(i);
				writer.write(str);
				writer.write("\n");
//				if (i < size - 1) {
//					writer.write("\n");
//				}
//				writer.close();

			}
		}

	}

}
