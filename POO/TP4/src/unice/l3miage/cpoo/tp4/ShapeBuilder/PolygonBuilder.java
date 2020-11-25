package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class PolygonBuilder extends ShapeBuilder {

    /**
     * Constructeur de la classe PolygonBuilder qui va permettre de créer les tableaux de Shapes et les tags de la Shape associée
     *
     * @param tags : Liste de String constituant les tags de la Shape
     */
    public PolygonBuilder(String[] tags) {
        super(tags);
    }

    // Methods

    /**
     * Renvoie un tableau de Polygone crée à partir des Tags du fichier SVG
     *
     * @return Tableau de Polygone extrait du fichier SVG
     */
    protected Polygone[] buildShapes() {
        ArrayList<Polygone> p = new ArrayList<>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;
        ArrayList<Vecteur> points;

        // Récupération des points
        for (String tag : shapeTags) {
            coordsStart = tag.indexOf("points=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 9);
            coordsStart += 8;

            coordsSubStr = tag.substring(coordsStart, coordsEnd);

            // On split les coordonées par rapport au espace/tab/etc...
            String[] pointsStr = coordsSubStr.split("(\\s+)");

            points = new ArrayList<>();
            for (String point : pointsStr) {
                points.add(new Vecteur(Double.parseDouble(point.split(",")[0]), Double.parseDouble(point.split(",")[1])));
            }
            p.add(new Polygone(points.toArray(new Vecteur[0])));
        }

        return p.toArray(new Polygone[0]);
    }

    /**
     * Renvoie le tableau de Polygone crée à partir de la méthode buildShapes()
     *
     * @return Tableau de Polygone
     */
    public Polygone[] getShapes() {
        if (this.shapes != null) {
            Polygone[] s = new Polygone[this.shapes.length];
            System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
            return s;
        } else {
            return null;
        }
    }
}
