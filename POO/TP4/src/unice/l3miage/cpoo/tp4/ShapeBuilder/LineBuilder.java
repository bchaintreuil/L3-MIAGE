package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Line;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class LineBuilder extends ShapeBuilder {
    public LineBuilder(String[] tags) {
        super(tags);
    }

    protected Line[] buildShapes() {
        ArrayList<Line> l = new ArrayList<>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;
        ArrayList<Double> pointsDouble = new ArrayList<>();

        // Récupération des points
        for(String tag: shapeTags) {
            // Pour x1
            coordsStart = tag.indexOf("x1=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour y1
            coordsStart = tag.indexOf("y1=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour x2
            coordsStart = tag.indexOf("x2=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            // Pour y2
            coordsStart = tag.indexOf("y2=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            pointsDouble.add(Double.parseDouble(coordsSubStr));

            for(Double point: pointsDouble) {
                l.add(new Line(new Vecteur(pointsDouble.get(0), pointsDouble.get(1)), new Vecteur(pointsDouble.get(2), pointsDouble.get(3))));
            }
        }

        return l.toArray(new Line[l.size()]);
    }

    public Line[] getShapes() {
        Line[] s = new Line[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
