package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

public class Polyline extends Shape {
    // Constructor variable
    private final Vecteur[] points;

    /**
     * Constructeur de la classe Polyline
     * @param points : Liste des points composants la Polyline
     */
    public Polyline(Vecteur... points) {
        this.points = new Vecteur[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    // Methods

    /**
     * Calcul impossible de l'aire d'une Polyline
     * @return Operation Exception
     */
    public double aire() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Calcul impossible du périmètre de la Polyline
     * @return Operation Exception
     */
    public double perimètre() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Calcul de la longueur de l'objet Polyline
     * @return La longueur de la Polyline
     */
    public double length() {
        double length = 0;
        for (int i = 0; i < this.points.length - 1; i++) {
            length += Vecteur.add(this.points[i + 1], this.points[i].opposé()).length();
        }
        return length;
    }

    // Getters and setters

    /**
     * Renvoie un tableau de Vecteur qui composent l'objet Polyline
     * @return Tableau de Vecteur composant l'objet Polyline
     */
    public Vecteur[] getPoints() {
        Vecteur[] points = new Vecteur[this.points.length];
        System.arraycopy(this.points, 0, points, 0, this.points.length);
        return points;
    }


}
