import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellChecker {
	/* The dictionary is case sensitive. */
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

	private String getSeachWord() {
		try (Scanner scan = new Scanner(System.in)) {
			System.out.print("Enter the word to search: ");
			String word = scan.next();
			while (word.trim().isEmpty()) {
				System.out.print("Enter the word to search: ");
				word = scan.next();
			}

			return word.trim();
		}
	}

	private void searchWord() {
		String word = getSeachWord();
		Iterable<String> iterable = dictionary.keysWithPrefix(word);
		List<String> matches = new ArrayList<>();
		String exactMatch = dictionary.get(word);
		
		for (String w : iterable) {
			matches.add(w);
		}
		
		System.out.printf("There are %d possible matches for %s \n", matches.size(), word);
		System.out.println("Suggestions");
		for (int i = 1; i <= 3 && matches.size() >= i; i++) {
			System.out.println(matches.get(i));
		}
		
		if (exactMatch != null) {
			System.out.println("Exact match found");
		} else {
			System.out.println("Exact match was not found!!");
		}
	}

	public void execute() throws IOException {
		populateDictionary();
		searchWord();
	}

	/**
	 * public boolean isCorrectWord(String word) {
	 * 
	 * Iterable<String> keysThatMatch = mytrie.keysThatMatch(word);
	 * 
	 * System.out.println("Absolute Match: "); for (String key : keysThatMatch) {
	 * System.out.println(key);
	 * 
	 * if (mytrie.keysThatMatch(word) ) { System.out.println("\n\t Spelling is
	 * correct for: " + word); return true; } else { System.out.print("\nSpelling
	 * for " + word + " is incorrect"); System.out.println(suggestWords(word));
	 * return false; }
	 * 
	 * }
	 * 
	 * }
	 */

}
