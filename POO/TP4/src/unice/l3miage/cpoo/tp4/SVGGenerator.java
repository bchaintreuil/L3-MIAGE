package unice.l3miage.cpoo.tp4;

import unice.l3miage.cpoo.tp4.Shape.Triangle;

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

    /*
     * Constructeur de la classe SVGGenerator
     * @param template : Sring du fichier SVG
     */
    public SVGGenerator(String template) {
        this.content = template.replace("</svg>", "");
    }

    /**
     * Ajoute la triangulation passée en argument au fichier SVG
     */
    public void addTriangulation(Triangle[] triangulation) {
        for(Triangle t: triangulation) {
            this.content += "<polygon fill=\"" + colors[this.nextColor] + "\" stroke=\"blue\" stroke-width=\"0\" points=\"";
            this.nextColor = (this.nextColor + 1) % colors.length;
            this.content += t.OA.get(0) + "," + t.OA.get(1) + " " + t.OB.get(0) + "," + t.OB.get(1) + " " + t.OC.get(0) + "," + t.OC.get(1) + "\"/>\n";
        }
    }

    /**
     * Affiche le SVG résultant
     * @return Le SVG final au format String
     */
    public String export() {
        return this.content + "</svg>";
    }
}
