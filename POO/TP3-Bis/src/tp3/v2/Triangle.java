package tp3.v2;


/**
 * Classe Triangle
 * @author Benjamin CHAINTREUIL
 *
 */

public class Triangle {
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
	
	/**
	 * Calcul du p�rim�tre du triangle
	 * @return Le p�rim�tre
	 */
	
	public double perimetre() {
		return Vecteur.add(OA, OB.oppos�()).length() + Vecteur.add(OA, OC.oppos�()).length() + Vecteur.add(OC, OB.oppos�()).length();
	}

	/**
	 * D�termination du barycentre du triangle
	 * @return Vecteur-point du barycentre
	 */
	
	public Vecteur barycentre() {
		return Vecteur.add(Vecteur.add(OA.multK(1/3), OB.multK(1/3)), OC.multK(1/3));
	}
	
	/**
	 * Calcul de la distance entre le barycentre et l'un des sommets du triangle.
	 * Par d�finition, le barycentre est � �quidistance de tout les sommets du triangle.
	 * Ainsi peut importe le sommet, la distance sera la m�me
	 * 
	 * @return Distance barycentre-sommet
	 */
	
	public double distanceCentre() {
		Vecteur barycentre = this.barycentre();
		Vecteur GA = Vecteur.add(OA, barycentre.oppos�());
		return GA.length();
	}
}
