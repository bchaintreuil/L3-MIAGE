package tp1.vecteur;

/**
 * Classe Bézier
 * @author Benjamin CHAINTREUIL
 *
 */

public class Bezier {
	Vecteur P0, P1, P2, P3;
	
	/**
	 * Constructeur de la classe
	 * @param P0 : Point définissant la courbe
	 * @param P1 : Point définissant la courbe
	 * @param P2 : Point définissant la courbe
	 * @param P3 : Point définissant la courbe
	 */

	Bezier (Vecteur P0, Vecteur P1, Vecteur P2, Vecteur P3) {
		this.P0 = P0;
		this.P1 = P1;
		this.P2 = P2;
		this.P3 = P3;
	}
	
	/**
	 * Calcul de la courbe de Bézier f(t)
	 * @param t : Paramètre réel appartenant à [0;1]
	 * @return Vecteur-point image par la fonction f
	 */

	public Vecteur calc(double t) {
		//assert (t < 0 || t > 1) : "Le paramètre t doit appartenir au domaine [0;1]"; // Tentative désastreuse de mise en place d'une assertion
		Vecteur f = new Vecteur(0, 0);
		f.x = Math.pow(t, 3) * P0.x + 3 * Math.pow(t, 2) * (1 - t) * P1.x + 3 * t * Math.pow((1-t), 2) * P2.x + Math.pow((1-t), 3) * P3.x;
		f.y = Math.pow(t, 3) * P0.y + 3 * Math.pow(t, 2) * (1 - t) * P1.y + 3 * t * Math.pow((1-t), 2) * P2.y + Math.pow((1-t), 3) * P3.y;
		return f;
	}
}
