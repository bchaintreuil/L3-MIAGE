package tp3.rendu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

public class Main {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
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

		String content = loadFile("D:\\POO\\poly.svg");
		//System.out.println(content);
		System.out.println("Nombre de balises = "+ compteBalises(content));
		var tab = content.split("<.+?>");
		System.out.println("Size : "+ tab.length);

		for(int i=0;i<tab.length;i++){
			System.out.println(tab[i]);
		}

	}
	
	
	
	// Methods
	
	private static void printHelp() {
		System.out.println("Help");
	}

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

	public static int compteBalises(String content){
		int temp=0;
		int compt=0;

		while(content.indexOf("<",temp) != -1){
			temp=content.indexOf("<",temp)+1;
			compt+=1;
		}
		return compt;
	}

	/* 
		<.+?>


	*/
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
