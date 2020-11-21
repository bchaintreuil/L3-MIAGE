package unice.l3miage.cpoo.tp4;

/**
 * Classe Triangle
 * @author Benjamin CHAINTREUIL
 *
 */

public class Triangle extends Shape {
    public Vecteur OA, OB, OC;

    /**
     * Constructeur de la classe
     * @param OA : Vecteur-point d'un sommet du triangle
     * @param OB : Vecteur-point d'un sommet du triangle
     * @param OC : Vecteur-point d'un sommet du triangle
     */

    public Triangle(Vecteur OA, Vecteur OB, Vecteur OC) {
        this.OA = OA;
        this.OB = OB;
        this.OC = OC;

    }

    public double aire() {
        // TODO : A CODER
        return 2;
    }

    /**
     * Détermination du barycentre du triangle
     * @return Vecteur-point du barycentre
     */

    public Vecteur barycentre() {
        return Vecteur.add(Vecteur.add(OA.multK(1/3), OB.multK(1/3)), OC.multK(1/3));
    }

    /**
     * Calcul de la distance entre le barycentre et l'un des sommets du triangle.
     * Par définition, le barycentre est à équidistance de tout les sommets du triangle.
     * Ainsi peut importe le sommet, la distance sera la même
     *
     * @return Distance barycentre-sommet
     */

    public double distanceCentre() {
        Vecteur barycentre = this.barycentre();
        Vecteur GA = Vecteur.add(OA, barycentre.opposé());
        return GA.length();
    }
}
