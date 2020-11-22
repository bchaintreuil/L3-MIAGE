package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.*;
import java.util.ArrayList;


public abstract class ShapeBuilder {
    protected String[] shapeTags;
    protected Shape[] shapes;

    public ShapeBuilder(String[] tags) {
        ArrayList<String> extractedTags = new ArrayList<String>();

        // This part is very tricky
        String childName = this.getClass().getSimpleName().replace("Builder", "").toLowerCase();

        for(String tag: tags) {
            if(tag.contains(childName)) {
                extractedTags.add(tag);
            }
        }

        if(!extractedTags.isEmpty()) { //
            this.shapeTags = extractedTags.toArray(new String[extractedTags.size()]);
            this.shapes = new Shape[shapeTags.length];
        }
    }

    abstract private buildShape();
    
}
