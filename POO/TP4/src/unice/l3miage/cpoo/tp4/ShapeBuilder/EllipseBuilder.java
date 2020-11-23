package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Ellipse;
import unice.l3miage.cpoo.tp4.Shape.Polygone;

public class EllipseBuilder extends ShapeBuilder {
    public EllipseBuilder(String[] tags) {
        super(tags);
    }

    protected Ellipse[] buildShapes() {
        return null;
    }

    public Ellipse[] getShapes() {
        Ellipse[] s = new Ellipse[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
