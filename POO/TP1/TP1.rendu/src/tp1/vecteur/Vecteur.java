package tp1.vecteur;

/**
 * 
 * Classe Vecteur
 * @author Benjamin CHAINTREUIL
 *
 */

public class Vecteur {
	public double x, y;
	
	/**
	 * Constructeur de la classe
	 * @param x : Composante x du vecteur
	 * @param y : Composante y du vecteur
	 */
	
	public Vecteur (double x, double y) {
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Calcul de la norme du vecteur
	 * @return Norme du vecteur
	 */

	public double length() {
		return Math.sqrt(x*x + y*y);
	}
	
	/**
	 * Multiplication d'un vecteur par un scalaire
	 * @param k : Un scalaire
	 * @return Le vecteur résultant de la multiplication par k
	 */
	
	public Vecteur multK(double k) {
		return new Vecteur(k*x, k*y);
	}
	
	/**
	 * Transposé d'un vecteur
	 * @return Le vecteur résultant de la transposition
	 */
	
	public Vecteur transpose() {
		return new Vecteur(y, x);
	}
	
	/**
	 * Opposé d'un vecteur
	 * @return Le vecteur opposé
	 */
	
	public Vecteur opposé() {
		return new Vecteur(-x, -y);
	}
	
	/**
	 * Addition de deux vecteurs
	 * @param v1 : Premier vecteur
	 * @param v2 : Second vecteur
	 * @return Le vecteur résultant de l'addition de v1 et v2
	 */
	
	public static Vecteur add(Vecteur v1, Vecteur v2) {
		return new Vecteur(v1.x + v2.x, v1.y + v2.y);
	}
	
	/**
	 * Soustraction de vecteur
	 * @param v1 : Premier vecteur
	 * @param v2 : Second vecteur
	 * @return Le vecteur résultant de la soustraction de v1 par v2
	 */
	
	public static Vecteur sub(Vecteur v1, Vecteur v2) {
		return new Vecteur(Vecteur.add(v1, v2.opposé()).x, Vecteur.add(v1, v2.opposé()).y);
	}
	
	/**
	 * Division par un scalaire
	 * @param v1 : Vecteur à diviser
	 * @param k : Scalaire
	 * @return Le vecteur résultant de la division de v1 par k
	 */
	
	public static Vecteur divK(Vecteur v1, double k) {
		return new Vecteur(v1.x/k, v1.y/k);
	}
	
	/**
	 * 
	 */
	
	public String toString() {
		return "<" + this.x + ", " + this.y + ">";
	}
	
	/**
	 * Produit scalaire de deux vecteurs
	 * @param v1 : Premier vecteur
	 * @param v2 : Second vecteur
	 * @return Le résultat du produit scalaire des deux vecteurs
	 */
	
	public static double produitScalaire(Vecteur v1, Vecteur v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}
	
	/**
	 * Norme du produit vectoriel de deux vecteurs
	 * @param v1 : Premier vecteur
	 * @param v2 : Second vecteur
	 * @return La norme du produit vectoriel des deux vecteurs
	 */
	
	public static double produitVectoriel(Vecteur v1, Vecteur v2) {
		return v1.x * v2.y - v1.y * v2.x;
	}
}

