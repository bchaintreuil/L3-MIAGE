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
		
		content = loadFile("C:\\Users\\Benjamin CHAINTREUIL\\Workspace\\L3-MIAGE\\POO\\TP3-Bis\\poly.svg");
		SVGParser parser = new SVGParser(content);
		
		System.out.println("Nombre de balise : " + parser.nbrTags());
		parser.displayTags();
	}
}
  

// ==> Load le fichier dans un string mutable 
//	V1 : Lire et afficher toutes les balises (on pourrait les stocker dans un tableau aussi)
//	V2 : V1 + enlever les balises <! ...> et <?...?>
//	Lire une chaîne de caractères au format SVG et construit un Polygone équivalent
//	Extraire les n-2 triangles du polygone et les affiche au format SVG. On pourra supposer que les polygones sont convexes.

//  regionMatches(int d1, String chaine, int d2, int l);
//  Compare la chaine à partir de la position d1 avec la chaine chaine à partir de la position d2 sur une longueur I.
//
//  String [] split(String pattern);
//  On peut découper une chaine en fonction d'un caractere de séparation
//
//  char charAt(int i);
//  Permet de savoir le char à une position précise
//  
//  String substring(int début, int fin);
//  String substring(int début);    
//  Renvoie la chaine de caractères présentes entre les index début et fin ou du début jusqu'à la fin de la chaine
//
//  int indexOf(String sousChaine);
//  int indexOf(String sousChaine, int debut);
//  Premier emplacement d'une sous chaine
//
//  int lastIndexOf(String sousChaine);
//  int lastIndexOf(String sousChaine, int debut);
//  Dernier emplacement d'une sous chaine
//
//  boolean endsWith(String sousChaine);
//  boolean startsWtih(String sousChaine);
//  Pour savoir si une chaine finie ou commence par une autre sous-chaine
// 
//  String trim();
//  Enleve les espaces en début et en fin de string
//
//  void delete(int début, int fin);
//  deleteCharAt(int index);
//  Permet d'effacer des chars dans un string
