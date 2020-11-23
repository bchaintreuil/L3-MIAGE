package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

public class Polyline extends Shape {
    // Constructor and constructor variable
    private Vecteur[] points;

    public Polyline(Vecteur... points) {
        this.points = new Vecteur[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    public double aire() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double perimètre() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented");
    }

    // TODO: getters and setters
}
