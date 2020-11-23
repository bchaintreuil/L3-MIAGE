package unice.l3miage.cpoo.tp4.ShapeBuilder;


import unice.l3miage.cpoo.tp4.Shape.Circle;
import unice.l3miage.cpoo.tp4.Shape.Polygone;

public class CircleBuilder extends ShapeBuilder {
    public CircleBuilder(String[] tags) {
        super(tags);
    }

    protected Circle[] buildShapes() {
        return null;
    }

    public Circle[] getShapes() {
        Circle[] s = new Circle[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
