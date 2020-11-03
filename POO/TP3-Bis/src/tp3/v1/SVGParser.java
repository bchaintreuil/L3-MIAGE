/**
 * 
 */
package tp3.v1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */
public class SVGParser {
	private String content;
	private final String tagRegex = "<[^>]+>";
	private String[] tags;

	private static String[] getRegexMatches(String str, String regex) {
		ArrayList<String> matches = new ArrayList<String>();
		Matcher m = Pattern.compile(regex).matcher(str);
		
		while(m.find()) {
			matches.add(m.group(0));
		}
		
		return matches.toArray(new String[0]);
	}

	private void parse() {
		this.tags = getRegexMatches(this.content, this.tagRegex);
	}
	
	public int nbrTags() {
		return this.tags.length;
	}
	
	public void displayTags() {
		for(int i = 0; i < this.nbrTags(); i++) {
			System.out.println("[Balise n°" + (i + 1) + "]");
			System.out.println(this.tags[i] + "\n");
		}
	}
	
	public SVGParser(String content) {
		this.content = new String(content);
		this.parse();
	}
	
}
