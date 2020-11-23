package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Ellipse;
import unice.l3miage.cpoo.tp4.Shape.Line;
import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class EllipseBuilder extends ShapeBuilder {
    public EllipseBuilder(String[] tags) {
        super(tags);
    }

    protected Ellipse[] buildShapes() {
        ArrayList<Ellipse> e = new ArrayList<>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;
        ArrayList<Double> pointsDouble = new ArrayList<>();

        // Récupération des points
        for(String tag: shapeTags) {
            // Pour cx et cy
            coordsStart = tag.indexOf("translate(");
            coordsEnd = tag.indexOf(")", coordsStart + 11)-2;
            // Il va récupérer les deux données du centre
            coordsSubStr = tag.substring(coordsStart, coordsEnd);

            // On split les coordonées par rapport au espace/tab/etc...
            String[] pointsStr = coordsSubStr.split("(\\s+)");

            pointsDouble.add(Double.parseDouble(pointsStr[0]));
            pointsDouble.add(Double.parseDouble(pointsStr[1]));

            // Pour rx
            coordsStart = tag.indexOf("rx=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour ry
            coordsStart = tag.indexOf("ry=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            for(Double point: pointsDouble) {
                e.add(new Ellipse(new Vecteur(pointsDouble.get(0), pointsDouble.get(1)), pointsDouble.get(2), pointsDouble.get(3)));
            }
        }

        return e.toArray(new Ellipse[e.size()]);
    }

    public Ellipse[] getShapes() {
        Ellipse[] s = new Ellipse[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
