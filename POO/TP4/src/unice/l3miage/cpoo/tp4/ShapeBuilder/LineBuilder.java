package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Line;
import unice.l3miage.cpoo.tp4.Shape.Polygone;

public class LineBuilder extends ShapeBuilder {
    public LineBuilder(String[] tags) {
        super(tags);
    }

    protected Line[] buildShapes() {
        return null;
    }

    public Line[] getShapes() {
        Line[] s = new Line[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
