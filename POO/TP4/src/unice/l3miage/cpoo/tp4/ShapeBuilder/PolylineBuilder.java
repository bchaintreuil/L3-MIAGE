package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Shape.Polyline;

public class PolylineBuilder extends ShapeBuilder {
    public PolylineBuilder(String[] tags) {
        super(tags);
    }

    protected Polyline[] buildShapes() {
        return null;
    }

    public Polyline[] getShapes() {
        Polyline[] s = new Polyline[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
