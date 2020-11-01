package tp3.v2;

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
		
		content = loadFile("C:\\Users\\Benjamin CHAINTREUIL\\Workspace\\L3-MIAGE\\POO\\TP3-Bis\\poly.svg"); //TODO: Args
		SVGParser parser = new SVGParser(content);
		
		System.out.println("Nombre de balise : " + parser.nbrTags());
		System.out.println("----- Balises -----");

		parser.displayTags();
		
		System.out.println("----- Polygones -----");
		Polygone[] p = parser.getPolygones();

		Triangle[][] triangulations = new Triangle[p.length][];
		
		for(int i = 0; i < p.length; i++) {
			System.out.println("===> Polygone #" + (i + 1));
			
			System.out.println("Nombre de points : " + p[i].nbrPoints());
			System.out.println("Périmètre : " + p[i].perimetre());
			System.out.print("Barycentre : ");
			p[i].barycentre().print();

			
			System.out.println("\nListe de points :");
			for(int j = 0; j < p[i].nbrPoints(); j++) {
				p[i].getPoint(j).print();
			}
			
			System.out.println("\nTriangulation en cours...");
			triangulations[i] = p[i].trianguler();
			System.out.println("Triangulation terminée !");
			System.out.println(triangulations[i].length + " triangles calculés.");
			
			System.out.println("\n----- Triangles du polygone #" + i + " -----");
			for(Triangle t: triangulations[i]) {
				t.OA.print();
				t.OB.print();
				t.OC.print();
				System.out.print("\n");
			}
		}
	}
}
