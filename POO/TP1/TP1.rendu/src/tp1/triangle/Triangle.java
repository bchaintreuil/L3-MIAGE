package tp1.triangle;

import tp1.vecteur.Vecteur;

/**
 * Classe Triangle
 * @author Benjamin CHAINTREUIL
 *
 */

public class Triangle {
	Vecteur OA, OB, OC;
	
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
	
	/**
	 * Calcul du périmètre du triangle
	 * @return Le périmètre
	 */
	
	public double perimetre() {
		return Vecteur.add(OA, OB.opposé()).length() + Vecteur.add(OA, OC.opposé()).length() + Vecteur.add(OC, OB.opposé()).length();
	}

	/**
	 * Détermination du barycentre du triangle
	 * @return Vecteur-point du barycentre
	 */
	
	public Vecteur barycentre() {
		return Vecteur.add(Vecteur.add(Vecteur.divK(OA, 3), Vecteur.divK(OB, 3)), Vecteur.divK(OC, 3));
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
		Vecteur AG = Vecteur.add(OA, barycentre.opposé());
		return AG.length();
	}
}
