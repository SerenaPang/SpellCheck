import java.io.IOException;

public class SpellCheckerMain {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Missing at least 1 argument");
			System.exit(1);// exit the program
		}

		String inputFile = args[0];
		String outputFile = args[1];
		System.out.println("Input file " + inputFile);
		System.out.println("Output file " + outputFile);

		SpellChecker spellChecker = new SpellChecker(inputFile, outputFile);
		spellChecker.execute();
	}
}
