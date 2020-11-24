package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Ellipse;
import unice.l3miage.cpoo.tp4.Vecteur;
import java.util.ArrayList;

public class EllipseBuilder extends ShapeBuilder {
    public EllipseBuilder(String[] tags) {
        super(tags);
    }

    protected Ellipse[] buildShapes() {
        ArrayList<Ellipse> e = new ArrayList<Ellipse>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;

        // Récupération des points
        for(String tag: shapeTags) {
            // Pour cx
            double cx = 0;
            if ((coordsStart = tag.indexOf("cx=\"")) != -1) {
                coordsEnd = tag.indexOf("\"", coordsStart + 5);
                coordsSubStr = tag.substring(coordsStart, coordsEnd);
                cx = Double.parseDouble(coordsSubStr);
            }

            // Pour cy
            double cy = 0;
            if ((coordsStart = tag.indexOf("cy=\"")) != -1) {
                coordsEnd = tag.indexOf("\"", coordsStart + 5);
                coordsSubStr = tag.substring(coordsStart, coordsEnd);
                cy = Double.parseDouble(coordsSubStr);
            }

            // Pour rx
            coordsStart = tag.indexOf("rx=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double rx = Double.parseDouble(coordsSubStr);

            // Pour ry
            coordsStart = tag.indexOf("ry=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 5);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double ry = Double.parseDouble(coordsSubStr);

            e.add(new Ellipse(new Vecteur(2, cx, cy), rx, ry));
        }

        return e.toArray(new Ellipse[e.size()]);
    }

    public Ellipse[] getShapes() {
        if (this.shapes != null) {
            Ellipse[] s = new Ellipse[this.shapes.length];
            System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
            return s;
        } else {
            return null;
        }
    }
}
