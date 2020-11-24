package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

public class Line extends Shape {
    private Vecteur OA, OB;

    /**
     * Constructeur de la classe
     * @param OA : Vecteur-point de départ de la ligne
     * @param OB : Vecteur-point d'arrivée de la ligne
     */

    public Line(Vecteur OA, Vecteur OB) { // TODO: Copy, not referencing - see triangle too
        this.OA = OA;
        this.OB = OB;
    }

    public double aire() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double perimètre() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Vecteur[] getPoints() {
        Vecteur[] points = {this.OA, this.OB};
        return points;
    }
    // TODO: getters and setters
}
