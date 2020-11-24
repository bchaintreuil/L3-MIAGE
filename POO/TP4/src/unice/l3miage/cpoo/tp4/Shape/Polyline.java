package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

public class Polyline extends Shape {
    // Constructor and constructor variable
    private final Vecteur[] points;

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

    public double length() {
        double length = 0;
        for (int i = 0; i < this.points.length - 1; i++) {
            length += Vecteur.add(this.points[i + 1], this.points[i].opposé()).length();
        }
        return length;
    }

    public Vecteur[] getPoints() {
        Vecteur[] points = new Vecteur[this.points.length];
        System.arraycopy(this.points, 0, points, 0, this.points.length);
        return points;
    }
}
