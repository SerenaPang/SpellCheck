import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellChecker {
	/* The dictionary is case sensitive. */
	private final TrieST<String> dictionary = new TrieST<String>();
	private final Path dictionaryPath;
	private final Path inputPath;
	private final Path outputPath;

	public SpellChecker(String inputFileName, String outputFileName) {
		// Assumes that english.0 is in the same directory that this class.
		this.dictionaryPath = Paths.get("english.0");
		this.inputPath = Paths.get(inputFileName);
		this.outputPath = Paths.get(outputFileName);
	}

	/** Reads the {@code inputFile} and populate the trie. */
	private void populateDictionary() throws IOException {
		System.out.println("Populating dictionary with " + dictionaryPath);
		List<String> words = FileReaderAndWriter.readFile(dictionaryPath);
		int index = 0;
		for (String word : words) {
			if (word.trim().isEmpty()) {
				continue;
			}
			dictionary.put(word, String.valueOf(index));
			index++;
		}

		System.out.println("# of words in the dictionary: " + dictionary.size());
		// System.out.println(dictionary);
	}

	private List<List<String>> processInputPath() throws IOException {
		List<String> wordsToSearch = FileReaderAndWriter.readFile(inputPath);
		List<List<String>> results = new ArrayList<>();
		for (String wordToSearch : wordsToSearch) {
			
			if (wordToSearch.trim().isEmpty()) {
				continue;
			}
			
			Iterable<String> iterable = dictionary.keysWithPrefix(wordToSearch);
			List<String> partialResults = convertTo(iterable);
			partialResults = selectOnly(partialResults, 3 /* numberOfWords */);
			String exactMatch = dictionary.get(wordToSearch);
			if (exactMatch != null && partialResults.contains(exactMatch)) {
				partialResults.remove(exactMatch);
				// add it to the end.
				partialResults.add(exactMatch);
			}
			results.add(partialResults);
		}

		return results;
	}

	private void createOutput(List<List<String>> results) throws IOException {
		System.out.println(results);
		FileReaderAndWriter.writeFile(outputPath, results);
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

	/** Select the specified number of words */
	private List<String> selectOnly(List<String> words, int numberOfWords) {
		List<String> result = new ArrayList<>();
		for (int i = 1; i <= numberOfWords && words.size() >= i; i++) {
			result.add(words.get(i - 1));
		}

		return result;
	}

	private List<String> convertTo(Iterable<String> iterable) {
		List<String> list = new ArrayList<>();
		for (String w : iterable) {
			list.add(w);
		}

		return list;
	}

	private void searchWord() {
		String word = getSeachWord();
		Iterable<String> iterable = dictionary.keysWithPrefix(word);
		List<String> matches = convertTo(iterable);
		String exactMatch = dictionary.get(word);

		System.out.printf("There are %d possible matches for %s \n", matches.size(), word);
		System.out.println("Suggestions");
		for (int i = 1; i <= 3 && matches.size() >= i; i++) {
			System.out.println(matches.get(i - 1));
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
		List<List<String>> results = processInputPath();
		createOutput(results);
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
