package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

public class Line extends Shape {
    // Constructor variables
    private final Vecteur OA;
    private final Vecteur OB;

    /**
     * Constructeur de la classe
     *
     * @param OA : Vecteur-point de départ de la ligne
     * @param OB : Vecteur-point d'arrivée de la ligne
     */
    public Line(Vecteur OA, Vecteur OB) {
        this.OA = OA;
        this.OB = OB;
    }

    // Methods

    /**
     * Calcul impossible de l'aire d'un objet Line
     *
     * @return Operation Exception
     */
    public double aire() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Calcul impossible du périmètre d'un objet Line
     *
     * @return Operation Exception
     */
    public double perimètre() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Calcul de la longueur de l'objet Line
     *
     * @return La longueur de l'objet Line
     */
    public double length() {
        return Vecteur.add(this.OB, this.OA.opposé()).length();
    }

    // Getters and setters

    /**
     * Renvoie un tableau de Vecteur qui composent l'objet Line
     *
     * @return Tableau de Vecteur
     */
    public Vecteur[] getPoints() {
        Vecteur[] points = {this.OA, this.OB};
        return points;
    }
}
