/**
 * 
 */
package tp3.rendu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * @author benchai
 *
 */
public class SVGParser {
	public static void parse(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		scanner.findAll("<*>");
		
	}
}
