package tp3.v2;

/**
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */
public class SVGGenerator {
	/*
	 * Vars
	 */
	private String content;
	private final String[] colors = {"lime", "yellow", "aqua", "red", "green"};
	private int nextColor = 0;
	
	/*
	 * Methods
	 */

	// Constructor
	public SVGGenerator(String template) {
		this.content = new String(template).replace("</svg>", "");
	}
	
	// Ajoute la triangulation passée en argument au fichier SVG
	public void addTriangulation(Triangle[] triangulation) {
		for(Triangle t: triangulation) {
			this.content += "<polygon fill=\"" + colors[this.nextColor] + "\" stroke=\"blue\" stroke-width=\"0\" points=\"";
			this.nextColor = (this.nextColor + 1) % colors.length;
			this.content += t.OA.get(0) + "," + t.OA.get(1) + " " + t.OB.get(0) + "," + t.OB.get(1) + " " + t.OC.get(0) + "," + t.OC.get(1) + "\"/>\n";
		}
	}
	
	// Affiche le SVG résultant
	public void export() {
		System.out.println(this.content + "</svg>");
	}
}
