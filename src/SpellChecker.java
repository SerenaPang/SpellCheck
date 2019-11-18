import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SpellChecker {
	private final TrieST<String> dictionary = new TrieST<String>();
	private final Path inputPath;
	private final Path outputPath;
	
	public SpellChecker(String inputFileName, String outputFileName) {
		this.inputPath = Paths.get(inputFileName);
		this.outputPath = Paths.get(outputFileName);
	}
	
	/** Reads the {@code inputFile} and populate the trie. */
	private void populateDictionary() throws IOException {
		System.out.println("Populating dictionary");
		List<String> words = FileReaderAndWriter.readFile(inputPath);
		int index = 0;
		for (String word : words) {
			dictionary.put(word, String.valueOf(index));
			index++;
		}
		
		System.out.println("# of words in the dictionary: " + dictionary.size());
		// System.out.println(dictionary);
	}
	
	public void execute() throws IOException {
		populateDictionary();
	}
	
/**
	public boolean isCorrectWord(String word) {

		Iterable<String> keysThatMatch = mytrie.keysThatMatch(word);

		System.out.println("Absolute Match: ");
		for (String key : keysThatMatch) {
			System.out.println(key);

			if (mytrie.keysThatMatch(word) ) {
				System.out.println("\n\t Spelling is correct for: " + word);
				return true;
			} else {
				System.out.print("\nSpelling for " + word + " is incorrect");
				System.out.println(suggestWords(word));
				return false;
			}

		}

	}*/
	

}
