package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;
import unice.l3miage.cpoo.tp4.iTrianguler;

/**
 * Classe Polygone
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */

public class Polygone extends Shape implements iTrianguler {
    // Fields
    private Vecteur[] points;

    // Constructor
    public Polygone(Vecteur... points) throws RuntimeException{
        if (points.length < 3) {
            throw new RuntimeException("Nbr de points < 3");
        }

        int dim = points[0].dimension();
        for(Vecteur point: points) {
            if(point.dimension() != dim) {
                throw new RuntimeException("Vecteurs de dim différentes");
            }
        }

        this.points = new Vecteur[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    // Getters and setters
    public Vecteur[] getPoints() {
        Vecteur[] foo = new Vecteur[this.points.length];
        System.arraycopy(this.points, 0, foo, 0, this.points.length);
        return foo;
    }

    public Vecteur getPoint(int i) throws RuntimeException{
        if (i > this.points.length) {
            throw new RuntimeException("i > nbr de points");
        } else {
            return this.points[i];
        }
    }

    public Polygone setPoint(Vecteur... points) {
        Vecteur[] new_points = new Vecteur[points.length + this.points.length];
        System.arraycopy(this.points, 0, new_points, 0, this.points.length);
        System.arraycopy(points, 0, new_points, this.points.length, points.length);
        return new Polygone(new_points);
    }

    // Methods

    // Retourne le nombre de points constituant le polygone
    public int nbrPoints() {
        return this.points.length;
    }

    // Détermine et renvoi le barycentre du polygone
    public Vecteur barycentre() {
        Vecteur foo = Vecteur.add(this.points);
        return foo.multK(1.0/this.points.length);
    }

    // Calcul et renvoi le périmètre du polygone
    public double perimètre() { // TODO: à tester
        double perimètre = 0;

        for(int i = 0; i < this.points.length; i++) {
            if (i < this.points.length - 1) {
                perimètre += Vecteur.add(this.points[i], this.points[i+1].opposé()).length();
            } else {
                perimètre += Vecteur.add(this.points[i], this.points[0].opposé()).length();
            }
        }
        return perimètre;
    }

    public double aire() { // TODO: à implémenter (avec triangulation)
        return 0;
    }
}

