package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

public class Rectangle extends Shape implements iToPolygone {
    // Constructor variables
    private final Vecteur anchor;
    private final double width;
    private final double height;

    /**
     * Constructeur de la classe Rectangle
     *
     * @param anchor : Vecteur-point de l'ancre du Rectangle
     * @param width  : Largeur du Rectangle
     * @param height : Hauteur du Rectangle
     */
    public Rectangle(Vecteur anchor, double width, double height) throws RuntimeException {
        if (anchor.dimension() != 2 || width <= 0 || height <= 0) {
            throw new RuntimeException("Vecteur de dim != 2 ou longueurs négatives/nulles");
        } else {
            this.anchor = anchor;
            this.width = width;
            this.height = height;
        }
    }

    // Methods

    /**
     * Calcul l'aire du Rectangle
     *
     * @return L'aire du Rectangle
     */
    public double aire() {
        return width * height;
    }

    /**
     * Calcul du périmètre du Rectangle
     *
     * @return Le périmètre du Rectangle
     */
    public double perimètre() {
        return 2 * (this.height + this.width);
    }

    /**
     * Calcul du point barycentre du Rectangle
     *
     * @return Le Vecteur-barycentre du Rectangle
     */
    public Vecteur barycentre() {
        return new Vecteur(2, anchor.get(0) + 0.5 * width, anchor.get(1) + 0.5 * height);
    }

    /**
     * Calcul de l'objet Polygone crée à partir de l'objet Rectangle
     *
     * @return L'objet Polygone crée à partir de l'objet Rectangle
     */
    public Polygone toPolygone() {
        Vecteur[] points = new Vecteur[4];

        points[0] = new Vecteur(2, anchor.get(0), anchor.get(1));
        points[1] = new Vecteur(2, anchor.get(0) + width, anchor.get(1));
        points[2] = new Vecteur(2, anchor.get(0) + width, anchor.get(1) + height);
        points[3] = new Vecteur(2, anchor.get(0), anchor.get(1) + height);

        return new Polygone(points);
    }
}
