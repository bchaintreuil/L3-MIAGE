package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Circle;
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

        // Récupération des points
        for(String tag: shapeTags) {
            // Pour cx
            double cx = 0;
            if ((coordsStart = tag.indexOf("cx=\"")) != -1) {
                coordsEnd = tag.indexOf("\"", coordsStart + 5);
                coordsStart += 4;
                coordsSubStr = tag.substring(coordsStart, coordsEnd);
                cx = Double.parseDouble(coordsSubStr);
            }

            // Pour cy
            double cy = 0;
            if ((coordsStart = tag.indexOf("cy=\"")) != -1) {
                coordsEnd = tag.indexOf("\"", coordsStart + 5);
                coordsStart += 4;
                coordsSubStr = tag.substring(coordsStart, coordsEnd);
                cy = Double.parseDouble(coordsSubStr);
            }

            // Pour radius
            coordsStart = tag.indexOf("r=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 4);
            coordsStart += 3;
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double radius = Double.parseDouble(coordsSubStr);

            c.add(new Circle(new Vecteur(2, cx, cy), radius));
        }

        return c.toArray(new Circle[0]);
    }

    public Circle[] getShapes() {
        if(this.shapes != null) {
            Circle[] s = new Circle[this.shapes.length];
            System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
            return s;
        } else {
            return null;
        }
    }
}
