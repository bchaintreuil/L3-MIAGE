package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

public class Rectangle extends Shape implements iToPolygone {
    // Fields
    private Vecteur anchor;
    private double width;
    private double height;

    // Constructor
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
    public double aire() { return width * height; }
    public double perimètre() { return 2 * (this.height + this.width); }
    public Vecteur barycentre() { return new Vecteur(2, anchor.get(0) + 1/2 * width, anchor.get(1) + 1/2 * height); }

    // TODO: getters and setters

    public Polygone toPolygone() {
        Vecteur[] points = new Vecteur[4];

        points[0] = new Vecteur(2, anchor.get(0), anchor.get(1));
        points[1] = new Vecteur(2, anchor.get(0) + width, anchor.get(1));
        points[2] = new Vecteur(2, anchor.get(0) + width, anchor.get(1) + height);
        points[3] = new Vecteur(2, anchor.get(0), anchor.get(1) + height);

        return new Polygone(points);
    }
}
