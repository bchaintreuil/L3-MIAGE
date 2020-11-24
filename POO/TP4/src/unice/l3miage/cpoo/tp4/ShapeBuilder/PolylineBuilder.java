package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Polyline;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class PolylineBuilder extends ShapeBuilder {

    /**
     * Constructeur de la classe PolylineBuilder qui va permettre de créer les tableaux de Shapes et les tags de la Shape associée
     * @param tags : Liste de String constituant les tags de la Shape
     */
    public PolylineBuilder(String[] tags) {
        super(tags);
    }

    // Methods

    /**
     * Renvoie un tableau de Polyline crée à partir des Tags du fichier SVG
     * @return Tableau de Polyline extrait du fichier SVG
     */
    protected Polyline[] buildShapes() {
        ArrayList<Polyline> p = new ArrayList<>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;
        ArrayList<Vecteur> points;

        // Récupération des points
        for(String tag: shapeTags) {
            coordsStart = tag.indexOf("points=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 9);
            coordsStart += 8;

            coordsSubStr = tag.substring(coordsStart, coordsEnd);

            // On split les coordonées par rapport au espace/tab/etc...
            String[] pointsStr = coordsSubStr.split("(\\s+)");

            points = new ArrayList<>();
            for(String point: pointsStr) {
                points.add(new Vecteur(Double.parseDouble(point.split(",")[0]), Double.parseDouble(point.split(",")[1])));
            }

            p.add(new Polyline(points.toArray(new Vecteur[0])));
        }

        return p.toArray(new Polyline[0]);
    }

    /**
     * Renvoie le tableau de Polyline crée à partir de la méthode buildShapes()
     * @return Tableau de Polyline
     */
    public Polyline[] getShapes() {
        if (this.shapes != null) {
            Polyline[] s = new Polyline[this.shapes.length];
            System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
            return s;
        } else {
            return null;
        }
    }
}
