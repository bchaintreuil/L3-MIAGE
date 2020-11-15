package tp4;

import java.util.ArrayList;

import rendu.shapes.Circle;
import tp3.v2.SVGParser;

// TODO : Add test files (?)

public class Main {
	
	// Vars
	public static String content;
	private final static String tagRegex = "<[^>]+>"; // Regex matchant les balises d'un fichier SVG
	
	public static void main(String[] args) {
		
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
						content = loadContent(args[1]);
						
						if(content == null || content.isBlank()) {
							System.out.println("Error: File is empty.");
							System.exit(1);
						} else {
							// Lecture du fichier
							SVGParser input = new SVGParser(content);
							
							// Affichage des balises
							System.out.println("Nombre de balise : " + input.nbrTags());
							System.out.println("----- Balises -----");

							input.displayTags();
							
							String[] tags = input.getTags(content, tagRegex);
							ArrayList<String> shapesTags = new ArrayList<String>();
							
							// TODO : Utile ?
							ArrayList<String> tagsAssociated = new ArrayList<String>();
							
							for(int i=0 ;i<tags.length; i++) {
								if(tags[i].contains("polygone") || tags[i].contains("polygon")) {
									tagsAssociated.add(tags[i]);
									shapesTags.add("polygone");
								}
								if(tags[i].contains("cercle") || tags[i].contains("circle")) {
									tagsAssociated.add(tags[i]);
									shapesTags.add("cercle");
								}
								if(tags[i].contains("rectangle") || tags[i].contains("rect")) {
									tagsAssociated.add(tags[i]);
									shapesTags.add("rectangle");
								}
								if(tags[i].contains("line") || tags[i].contains("ligne")) {
									tagsAssociated.add(tags[i]);
									shapesTags.add("ligne");
								}
								if(tags[i].contains("polyline")){
									tagsAssociated.add(tags[i]);
									shapesTags.add("polyline");
								}
								if(tags[i].contains("ellipse")) {
									tagsAssociated.add(tags[i]);
									shapesTags.add("ellipse");
								}
							}
							
							if(shapesTags.size() == 0 || shapesTags.isEmpty()) {
								// TODO : Build a better error message
								System.out.println("Error, invalid shapes SVG");																
							}
							
							String[] convertedShapesTags = (String[]) shapesTags.toArray();
							String[] convertedTagsAssociated = (String[]) tagsAssociated.toArray();
							
							for(int j=0; j<shapesTags.size(); j++) {
								switch(convertedShapesTags[j]) {
									case "polygone":
										break;
									case "cercle":
										Circle.trianguler();
										break;	
									case "rectangle":
										Rect.trianguler();
										break;
									case "ligne":
										Line.trianguler();
										break;
									case "polyline":
										Polyline.trianguler();
										break;
									case "ellipse":
										Ellipse.trianguler();
										break;
									default:
										// TODO : Show mesage error ?
										break;
								}
							}
	 
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
	
	// TODO : Faire une classe FileUtil ????? 
	
	// Stocke les balises et les affiche
	public static String loadContent(String path) {
		String content = tp3.v2.Main.loadFile(path);

		if(content == null || content.isBlank() || content.isEmpty()) {
			System.out.println("Error: File is empty.");
			return null;
		}
		else {
			// Lecture du fichier
			SVGParser input = new SVGParser(content);
					
			// Affichage des balises
			System.out.println("Nombre de balise : " + input.nbrTags());
			System.out.println("----- Balises -----");

			input.displayTags();
					
			return content;
		}
	};
	
	// Help printer
	public static void printHelp() {
		System.out.println("Utilisation :\n --help, -h \n --input <path to svg file> \n Fait par CHAINTREUIL Benjamin et DELMARE Thomas.");
	}
		
}
