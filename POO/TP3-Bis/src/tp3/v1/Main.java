package tp3.v1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */
public class Main {
	// Vars
	static String content;
	
	// Methods
	
	/*
	 * Help
	 */
	private static void printHelp() { //TODO: Help method
		System.out.println("Help");
	}

	/*
	 * Méthode gérant l'ouverture du fichier puis --> string
	 */
	public static String loadFile(String path) {
	    try {
	       File fichier = new File(path);
	       BufferedInputStream in = new BufferedInputStream(new FileInputStream(fichier));
	       StringWriter out = new StringWriter();
	       int b;
	       while ((b=in.read()) != -1)
	           out.write(b);
	       out.flush();
	       out.close();
	       in.close();
	       return out.toString();
	    }
	    catch (IOException ie)
	    {
	         ie.printStackTrace(); 
	    }
	    return null;
	}

	// Main
	public static void main(String[] args) throws IOException {
		if (args.length > 1) {
			switch(args[0]) {
				case "-h":
					printHelp();
					break;
				case "--help":
					printHelp();
					break;
				case "--input":
					// TODO : args[1] is a valid path and no more args
					content = loadFile(args[1]);
					
					break;
				default:
					printHelp();
					break;
			}
		} else {
			printHelp();
		}
		
		if(content == null) {
			content = loadFile("poly.svg");
		}
		SVGParser parser = new SVGParser(content);
		
		System.out.println("Nombre de balise : " + parser.nbrTags());
		parser.displayTags();
	}
}