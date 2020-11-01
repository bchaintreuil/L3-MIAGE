package tp3.v2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */
public class SVGParser {
	private final String tagRegex = "<[^>]+>";
	private String content;
	private String[] tags;
	private Polygone[] polygones;

	private String[] getTags(String str, String regex) {
		ArrayList<String> matches = new ArrayList<String>();
		Matcher m = Pattern.compile(regex).matcher(str);
		
		while(m.find()) {
			// Removing comments and header.
			if(m.group(0).contains("<?") || m.group(0).contains("<!--")) {
				continue;
			}
			
			matches.add(m.group(0));
		}
		
		return matches.toArray(new String[0]);
	}
	
	private Polygone[] generatePolygones() {
		ArrayList<Polygone> p = new ArrayList<Polygone>();
		int coordsStart;
		int coordsEnd;
		String coordsSubStr;
		ArrayList<Vecteur> points;
		
		for(int i = 0; i < this.tags.length; i++) {
			if(tags[i].contains("polygon")) {
				coordsStart = tags[i].indexOf("points=\"");
				coordsEnd = tags[i].indexOf("\"", coordsStart + 9);
				coordsStart += 8;

				coordsSubStr = tags[i].substring(coordsStart, coordsEnd);
				
				// On split les coordonées par rapport au espace/tab/etc...
				String[] pointsStr = coordsSubStr.split("(\\s+)");
				
				points = new ArrayList<Vecteur>();
				for(String point: pointsStr) {
					points.add(new Vecteur(Double.parseDouble(point.split(",")[0]), Double.parseDouble(point.split(",")[1])));
				}
				
				p.add(new Polygone(points.toArray(new Vecteur[0])));
			}
			
		}
		
		return p.toArray(new Polygone[0]);
	}
	
	public Polygone[] getPolygones() {
		Polygone[] p = new Polygone[this.polygones.length];
		System.arraycopy(this.polygones, 0, p, 0, this.polygones.length);
		return p;
	}

	private void parse() {
		this.tags = this.getTags(this.content, this.tagRegex);
		this.polygones = this.generatePolygones();
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
