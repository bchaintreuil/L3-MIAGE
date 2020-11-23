package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.*;
import java.util.ArrayList;


public abstract class ShapeBuilder {
    protected String[] shapeTags;
    protected Shape[] shapes;

    protected ShapeBuilder(String[] tags) {
        // Default to null for child differenciation
        this.shapeTags = null;
        this.shapes = null;

        ArrayList<String> extractedTags = new ArrayList<String>();

        // This part is very tricky
        String childName = this.getClass().getSimpleName().replace("Builder", "").toLowerCase();

        for(String tag: tags) {
            if(tag.contains(childName)) {
                extractedTags.add(tag);
            }
        }

        if(!extractedTags.isEmpty()) { // TODO: Check in case of none
            this.shapeTags = extractedTags.toArray(new String[extractedTags.size()]);
            this.shapes = this.buildShapes();
        }
    }

    abstract protected Shape[] buildShapes();

    public Shape[] getShapes() {
        Shape[] s = new Shape[this.shapes.length];
        System.arraycopy(this.shapes, 0, s, 0, this.shapes.length);
        return s;
    }
}
