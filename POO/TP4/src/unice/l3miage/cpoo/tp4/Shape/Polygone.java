package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;
import unice.l3miage.cpoo.tp4.iTrianguler;

/**
 * Classe Polygone
 *
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */

public class Polygone extends Shape implements iTrianguler {
    // Constructor variables
    private final Vecteur[] points;

    /**
     * Constructeur de la classe Polygone
     * @param points : Tableau de Vecteur permettant de construire l'objet Polygone
     */
    public Polygone(Vecteur... points) throws RuntimeException {
        if (points.length < 3) {
            throw new RuntimeException("Nbr de points < 3");
        }

        int dim = points[0].dimension();
        for (Vecteur point : points) {
            if (point.dimension() != dim) {
                throw new RuntimeException("Vecteurs de dim différentes");
            }
        }

        this.points = new Vecteur[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    // Methods

    /**
     * Renvoie le nombre de points dans le Polygone
     * @return Le nombre de points dans le Polygone
     */
    public int nbrPoints() {
        return this.points.length;
    }

    /**
     * Renvoie le vecteur Barycentre du Polygone
     * @return Le vecteur Barycentre du Polygone
     */
    public Vecteur barycentre() {
        Vecteur foo = Vecteur.add(this.points);
        return foo.multK(1.0 / this.points.length);
    }

    /**
     * Calcul du périmètre du Polygone
     * @return Le périmètre du Polygone
     */
    public double perimètre() {
        double perimètre = 0;

        for (int i = 0; i < this.points.length; i++) {
            if (i < this.points.length - 1) {
                perimètre += Vecteur.add(this.points[i], this.points[i + 1].opposé()).length();
            } else {
                perimètre += Vecteur.add(this.points[i], this.points[0].opposé()).length();
            }
        }
        return perimètre;
    }

    /**
     * Calcule l'aire du Polygone
     * @return L'aire du Polygone
     */
    public double aire() {
        double aire = 0;
        Triangle[] triangulation = this.trianguler();
        for (Triangle t : triangulation) {
            aire += t.aire();
        }
        return aire;
    }

    // Getters and setters

    /**
     * Renvoie un tableau de Vecteur qui composent l'objet Polygone
     * @return Tableau de Vecteur composant l'objet Polygone
     */
    public Vecteur[] getPoints() {
        Vecteur[] foo = new Vecteur[this.points.length];
        System.arraycopy(this.points, 0, foo, 0, this.points.length);
        return foo;
    }

    /**
     * Renvoie un Vecteur correspondant au point numéro i du Polygone
     * @return Le point i du Polygone, sous forme de Vecteur
     */
    public Vecteur getPoint(int i) throws RuntimeException {
        if (i > this.points.length) {
            throw new RuntimeException("i > nbr de points");
        } else {
            return this.points[i];
        }
    }

    /**
     * Renvoie l'objet Polygone crée à partir de la modification du Polygone actuel
     * @return Objet Polygone
     */
    public Polygone setPoint(Vecteur... points) {
        Vecteur[] new_points = new Vecteur[points.length + this.points.length];
        System.arraycopy(this.points, 0, new_points, 0, this.points.length);
        System.arraycopy(points, 0, new_points, this.points.length, points.length);
        return new Polygone(new_points);
    }
}

