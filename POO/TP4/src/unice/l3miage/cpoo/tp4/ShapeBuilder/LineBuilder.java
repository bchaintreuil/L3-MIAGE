package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Line;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class LineBuilder extends ShapeBuilder {
    public LineBuilder(String[] tags) {
        super(tags);
    }

    protected Line[] buildShapes() {
        ArrayList<Line> l = new ArrayList<Line>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;

        // Récupération des points
        for(String tag: shapeTags) {
            if (tag.contains("polyline")) { // Exclusion for polyline tag
                continue;
            }

            // Pour x1
            coordsStart = tag.indexOf("x1=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsStart += 4;
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double x1 = Double.parseDouble(coordsSubStr);

            // Pour y1
            coordsStart = tag.indexOf("y1=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsStart += 4;
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double y1 = Double.parseDouble(coordsSubStr);

            // Pour x2
            coordsStart = tag.indexOf("x2=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsStart += 4;
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double x2 = Double.parseDouble(coordsSubStr);

            // Pour y2
            coordsStart = tag.indexOf("y2=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsStart += 4;
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double y2 = Double.parseDouble(coordsSubStr);

            l.add(new Line(new Vecteur(2, x1, y1), new Vecteur(2, x2, y2)));
        }

        return l.toArray(new Line[l.size()]);
    }

    public Line[] getShapes() {
        if (this.shapes != null) {
            Line[] s = new Line[this.shapes.length];
            System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
            return s;
        } else {
            return null;
        }
    }
}
