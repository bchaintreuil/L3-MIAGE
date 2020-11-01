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
	private static String content;
	
	// Methods
	
	/*
	 * Help
	 */
	private static void printHelp() {
		System.out.println("Utilisation :\n --help, -h \n --input <path to svg file> \n Fait par CHAINTREUIL Benjamin et DELMARE Thomas.");
	}

	/*
	 * Méthode gérant l'ouverture du fichier puis --> string
	 */
	public static String loadFile(String path) {
	    try {
	       File file = new File(path);
	       if(file.isFile()) {
		       BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		       StringWriter out = new StringWriter();
		       int b;
		       
		       while ((b=in.read()) != -1) {
		           out.write(b);
		       }
		       
		       out.flush();
		       out.close();
		       in.close();
		       return out.toString(); 
	       } else {
	    	   System.out.println("Error: Enter a valid file path.");
	    	   System.exit(1);
	       }
	    } catch (IOException ie) {
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
					if(args.length == 2) {
						content = loadFile(args[1]);
						
						if(content == null || content.isBlank()) {
							System.out.println("Error: File is empty.");
							System.exit(1);
						} else {
							SVGParser input = new SVGParser(content);
							
							System.out.println("Nombre de balise : " + input.nbrTags());
							System.out.println("----- Balises -----");

							input.displayTags();
							
							System.out.println("----- Polygones -----");
							Polygone[] p = input.getPolygones();

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
								
								System.out.println("\n----- Triangles du polygone #" + (i + 1) + " -----");
								for(Triangle t: triangulations[i]) {
									t.OA.print();
									t.OB.print();
									t.OC.print();
									System.out.print("\n");
								}
							}
							
							// Génération du SVG
							System.out.println("----- Génération du SVG -----");
							SVGGenerator output = new SVGGenerator(content);
							
							for(Triangle[] triangulation: triangulations) {
								output.addTriangulation(triangulation);
							}
							
							output.export();
							
							System.out.println("\n----- All done ! =D -----");
						}
						break;
					}
					printHelp();
					break;
				default:
					printHelp();
					break;
			}
		} else {
			printHelp();
		}
	}
}
