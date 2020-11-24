package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class PolygonBuilder extends ShapeBuilder {
    public PolygonBuilder(String[] tags) {
        super(tags);
    }

    // Génération des polygones extraits du fichier SVG
    protected Polygone[] buildShapes() {
        ArrayList<Polygone> p = new ArrayList<>();
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
            p.add(new Polygone(points.toArray(new Vecteur[0])));
        }

        return p.toArray(new Polygone[0]);
    }

    public Polygone[] getShapes() {
        if(this.shapes != null) {
            Polygone[] s = new Polygone[this.shapes.length];
            System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
            return s;
        } else {
            return null;
        }
    }
}
