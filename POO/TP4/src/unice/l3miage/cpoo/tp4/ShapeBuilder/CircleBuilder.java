package unice.l3miage.cpoo.tp4.ShapeBuilder;


import unice.l3miage.cpoo.tp4.Shape.Circle;
import unice.l3miage.cpoo.tp4.Shape.Line;
import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class CircleBuilder extends ShapeBuilder {
    public CircleBuilder(String[] tags) {
        super(tags);
    }

    protected Circle[] buildShapes() {
        ArrayList<Circle> c = new ArrayList<>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;
        ArrayList<Double> pointsDouble = new ArrayList<>();

        // Récupération des points
        for(String tag: shapeTags) {
            // Pour cx
            coordsStart = tag.indexOf("cx=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour cy
            coordsStart = tag.indexOf("cy=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour r
            coordsStart = tag.indexOf("r=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 4);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            for(Double point: pointsDouble) {
                c.add(new Circle(new Vecteur(pointsDouble.get(0), pointsDouble.get(1)), pointsDouble.get(2)));
            }
        }

        return c.toArray(new Circle[c.size()]);
    }

    public Circle[] getShapes() {
        Circle[] s = new Circle[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
