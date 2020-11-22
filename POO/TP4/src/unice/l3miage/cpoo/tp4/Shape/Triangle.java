package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

/**
 * Classe Triangle
 * @author Benjamin CHAINTREUIL
 *
 */

public class Triangle extends Shape {
    // Fields
    public Vecteur OA, OB, OC;

    // Constructor
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

    // Methods
    /**
     * Détermination de l'aire du triangle
     * @return L'aire du triangle
     */

    public double aire() { return 1/2 * Vecteur.produitVectoriel(Vecteur.add(this.OA.opposé(), this.OB), Vecteur.add(this.OA.opposé(), this.OC)).length(); }

    /**
     * Détermination du barycentre du triangle
     * @return Vecteur-point du barycentre
     */

    public Vecteur barycentre() { return Vecteur.add(Vecteur.add(this.OA.multK(1/3), this.OB.multK(1/3)), this.OC.multK(1/3)); }

    public double perimètre() { return this.OA.length() + this.OB.length() + this.OC.length(); }

    /**
     * Calcul de la distance entre le barycentre et l'un des sommets du triangle.
     * Par définition, le barycentre est à équidistance de tout les sommets du triangle.
     * Ainsi peut importe le sommet, la distance sera la même
     *
     * @return Distance barycentre-sommet
     */

    public double distanceCentre() {
        Vecteur barycentre = this.barycentre();
        Vecteur GA = Vecteur.add(this.OA, barycentre.opposé());
        return GA.length();
    }
}
