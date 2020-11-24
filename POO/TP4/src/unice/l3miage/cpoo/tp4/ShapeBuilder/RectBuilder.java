package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Rectangle;
import unice.l3miage.cpoo.tp4.Vecteur;
import java.util.ArrayList;

public class RectBuilder extends ShapeBuilder {
    public RectBuilder(String[] tags) {
        super(tags);
    }

    protected Rectangle[] buildShapes() {
        ArrayList<Rectangle> r = new ArrayList<Rectangle>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;

        // Récupération des points
        for(String tag: shapeTags) {
            // Pour x
            double x = 0;
            if ((coordsStart = tag.indexOf("x=\"")) != -1) {
                coordsEnd = tag.indexOf("\"", coordsStart + 4);
                coordsSubStr = tag.substring(coordsStart, coordsEnd);

                x = Double.parseDouble(coordsSubStr);
            }

            // Pour y
            double y = 0;
            if ((coordsStart = tag.indexOf("y=\"")) != -1) {
                coordsEnd = tag.indexOf("\"", coordsStart + 4);
                coordsSubStr = tag.substring(coordsStart, coordsEnd);
                y = Double.parseDouble(coordsSubStr);
            }

            // Pour width
            coordsStart = tag.indexOf("width=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 8);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double width = Double.parseDouble(coordsSubStr);

            // Pour height
            coordsStart = tag.indexOf("height=\"");
            coordsEnd = tag.indexOf("\"", coordsStart + 9);
            coordsSubStr = tag.substring(coordsStart, coordsEnd);
            double height = Double.parseDouble(coordsSubStr);

            r.add(new Rectangle(new Vecteur(2, x, y), width, height));
        }

        return r.toArray(new Rectangle[r.size()]);
    }

    public Rectangle[] getShapes() {
        if (this.shapes != null) {
            Rectangle[] s = new Rectangle[this.shapes.length];
            System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
            return s;
        } else {
            return null;
        }
    }
}
