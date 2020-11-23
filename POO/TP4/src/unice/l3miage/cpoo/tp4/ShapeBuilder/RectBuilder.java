package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Shape.Rectangle;

public class RectBuilder extends ShapeBuilder {
    public RectBuilder(String[] tags) {
        super(tags);
    }

    protected Rectangle[] buildShapes() {
        return null;
    }

    public Rectangle[] getShapes() {
        Rectangle[] s = new Rectangle[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
