package tp2.rendu;

/**
 * 
 * Classe Vecteur2D
 * @author Benjamin CHAINTREUIL
 *
 */

public class Vecteur2D {
	public double x, y;
	
	/**
	 * Constructeur de la classe
	 * @param x : Composante x du vecteur
	 * @param y : Composante y du vecteur
	 */
	
	public Vecteur2D (double x, double y) {
		this.x = x;
		this.y = y;
		
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
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
	
	public Vecteur2D multK(double k) {
		return new Vecteur2D(k*x, k*y);
	}
	
	/**
	 * Transposé d'un vecteur
	 * @return Le vecteur résultant de la transposition
	 */
	
	public Vecteur2D transpose() {
		return new Vecteur2D(y, x);
	}
	
	/**
	 * Opposé d'un vecteur
	 * @return Le vecteur opposé
	 */
	
	public Vecteur2D opposé() {
		return new Vecteur2D(-x, -y);
	}
	
	/**
	 * Addition de deux vecteurs
	 * @param v1 : Premier vecteur
	 * @param v2 : Second vecteur
	 * @return Le vecteur résultant de l'addition de v1 et v2
	 */
	
	public static Vecteur2D add(Vecteur2D v1, Vecteur2D v2) {
		return new Vecteur2D(v1.x + v2.x, v1.y + v2.y);
	}
	
	/**
	 * Soustraction de vecteur
	 * @param v1 : Premier vecteur
	 * @param v2 : Second vecteur
	 * @return Le vecteur résultant de la soustraction de v1 par v2
	 */
	
	public static Vecteur2D sub(Vecteur2D v1, Vecteur2D v2) {
		return new Vecteur2D(Vecteur2D.add(v1, v2.opposé()).x, Vecteur2D.add(v1, v2.opposé()).y);
	}
	
	/**
	 * Division par un scalaire
	 * @param v1 : Vecteur à diviser
	 * @param k : Scalaire
	 * @return Le vecteur résultant de la division de v1 par k
	 */
	
	public static Vecteur2D divK(Vecteur2D v1, double k) {
		return new Vecteur2D(v1.x/k, v1.y/k);
	}
	
	/**
	 * 
	 */
	
	@Override
	public String toString() {
		return "<" + this.x + ", " + this.y + ">";
	}
	
	/**
	 * Produit scalaire de deux vecteurs
	 * @param v1 : Premier vecteur
	 * @param v2 : Second vecteur
	 * @return Le résultat du produit scalaire des deux vecteurs
	 */
	
	public static double produitScalaire(Vecteur2D v1, Vecteur2D v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}
	
	/**
	 * Norme du produit vectoriel de deux vecteurs
	 * @param v1 : Premier vecteur
	 * @param v2 : Second vecteur
	 * @return La norme du produit vectoriel des deux vecteurs
	 */
	
	public static double produitVectoriel(Vecteur2D v1, Vecteur2D v2) {
		return v1.x * v2.y - v1.y * v2.x;
	}
}

