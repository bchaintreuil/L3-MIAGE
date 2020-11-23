package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Circle;
import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Shape.Rectangle;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class RectBuilder extends ShapeBuilder {
    public RectBuilder(String[] tags) {
        super(tags);
    }

    protected Rectangle[] buildShapes() {
        ArrayList<Rectangle> r = new ArrayList<>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;
        ArrayList<Double> pointsDouble = new ArrayList<>();

        // Récupération des points
        for(String tag: shapeTags) {
            // Pour x
            coordsStart = tag.indexOf("x=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 4);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour y
            coordsStart = tag.indexOf("y=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 4);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour width
            coordsStart = tag.indexOf("width=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 8);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour height
            coordsStart = tag.indexOf("height=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 9);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));


            for(Double point: pointsDouble) {
                r.add(new Rectangle(new Vecteur(pointsDouble.get(0), pointsDouble.get(1)), pointsDouble.get(2), pointsDouble.get(3)));
            }
        }

        return r.toArray(new Rectangle[r.size()]);
    }

    public Rectangle[] getShapes() {
        Rectangle[] s = new Rectangle[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
