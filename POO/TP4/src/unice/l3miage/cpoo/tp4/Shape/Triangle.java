package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

/**
 * Classe Triangle
 * @author Benjamin CHAINTREUIL
 *
 */

public class Triangle extends Shape {
    // Constructor variables
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

    // Methods

    /**
     * Détermination de l'aire du triangle
     *
     * @return L'aire du triangle
     */
    public double aire() {
        double AB = Vecteur.add(this.OB, this.OA.opposé()).length();
        double BC = Vecteur.add(this.OC, this.OB.opposé()).length();
        double CA = Vecteur.add(this.OA, this.OC.opposé()).length();
        double p = (AB + BC + CA) / 2;
        return Math.sqrt(p * (p - AB) * (p - BC) * (p - CA)); // Formule d'Héron
    }

    /**
     * Détermination du barycentre du triangle
     * @return Vecteur-point du barycentre
     */
    public Vecteur barycentre() { return Vecteur.add(Vecteur.add(this.OA.multK(1/3), this.OB.multK(1/3)), this.OC.multK(1/3)); }

    /**
     * Calcul du périmètre du Triangle
     * @return Le périmètre du Triangle
     */
    public double perimètre() {
        double AB = Vecteur.add(this.OB, this.OA.opposé()).length();
        System.out.println(AB);
        double BC = Vecteur.add(this.OC, this.OB.opposé()).length();
        System.out.println(BC);
        double CA = Vecteur.add(this.OA, this.OC.opposé()).length();
        System.out.println(CA);
        double p = (AB + BC + CA);
        return p;
    }
    // public double perimètre() { return this.OA.length() + this.OB.length() + this.OC.length(); }

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
