package unice.l3miage.cpoo.tp4.ShapeBuilder;

import unice.l3miage.cpoo.tp4.Shape.Shape;

import java.util.ArrayList;


public abstract class ShapeBuilder {
    protected String[] shapeTags;
    protected Shape[] shapes;

    /**
     * Constructeur de la classe ShapeBuilder qui va permettre de créer les tableaux de Shapes et les tags de la Shape associée
     *
     * @param tags : Liste de String constituant les tags de la Shape
     */
    protected ShapeBuilder(String[] tags) {
        // Default to null for child differenciation
        this.shapeTags = null;
        this.shapes = null;

        ArrayList<String> extractedTags = new ArrayList<>();

        // This part is very tricky
        String childName = this.getClass().getSimpleName().replace("Builder", "").toLowerCase();

        for (String tag : tags) {
            if (tag.contains(childName)) {
                extractedTags.add(tag);
            }
        }

        if (!extractedTags.isEmpty()) {
            this.shapeTags = extractedTags.toArray(new String[0]);
            this.shapes = this.buildShapes();
        }
    }

    // Methods

    /**
     * Renvoie un tableau de la Shape utilisée crée à partir des Tags du fichier SVG
     *
     * @return Tableau de la Shape utilisée extrait du fichier SVG
     */
    abstract protected Shape[] buildShapes();

    /**
     * Renvoie le tableau de Shape utilisée crée à partir de la méthode buildShapes()
     *
     * @return Tableau de la Shape utilisée
     */
    abstract public Shape[] getShapes();
}
