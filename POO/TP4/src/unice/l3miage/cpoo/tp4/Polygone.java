package unice.l3miage.cpoo.tp4;

import java.util.ArrayList;

/**
 * Classe Polygone
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */

public class Polygone extends Shape {
    // Constructor and constructor variable
    private Vecteur[] points;

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

    /*
     * Methods
     */

    // Retourne l'aire du Polygone
    @Override
    public double aire() {
        double aire = 0;

        ArrayList<Triangle> liste_triangles = new ArrayList<Triangle>();
        liste_triangles = iTrianguler.trianguler(liste_triangles);

        for(int i=0; i<liste_triangles.size();i++){
            aire += liste_triangles.get(i).aire();
        }

        return aire;
    }

    // Retourne le nombre de points constituant le polygone
    public int nbrPoints() {
        return this.points.length;
    }

    // Détermine et renvoi le barycentre du polygone
    public Vecteur barycentre() {
        Vecteur foo = Vecteur.add(this.points);
        return foo.multK(1.0/this.points.length);
    }
}

