package tp3.rendu;

import java.io.File;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 1) {
			switch(args[0]) {
				case "-h":
					printHelp();
					break;
				case "--help":
					printHelp();
					break;
				case "-i":
					// TODO : args[1] is a valid path and no more args
					File file = new File(args[1]);
					SVGParser.parse(file);
					break;
				default:
					printHelp();
					break;
			}
		} else {
			printHelp();
		}
	}

	
	private static void printHelp() {
		System.out.println("Help");
	}
}
