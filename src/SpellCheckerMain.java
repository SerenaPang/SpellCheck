import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SpellCheckerMain {
	
	
	//This method receive 2 parameters (input/output files)
	
	
	public static void spellCheckerRun(String inputFile, String outPutFile) {
		
		Path inputPath = Paths.get(inputFile);
		Path outputPath= Paths.get(outPutFile);
		try {
			
			//FileOutput.outputResult(inputPath);
			FileReaderAndWriter.readFile(inputPath);
			FileReaderAndWriter.writeFile(outputPath);
		} catch (IOException e) {
			System.err.println(
					"Unable to print out to file: " + inputPath.toString() + "\n\tPlease check your argument.");
		}
	}

	//java CS245A1 input.txt output.txt
	public static void main(String[] args) {
		
		if(args.length == 2) {
	
		SpellCheckerMain.spellCheckerRun(args[0], args[1]);
		System.out.println("read input file and generate output file");
		
		}
		else {		
			System.out.println("Missing at least 1 argument");
			System.exit(1);//exit the program
		}
				
		
	
		
		
	}
}
