package tp1.vecteur;

import frame.FrameHelper; 
import java.awt.Graphics;
import java.awt.Color;

/**
 * Classe DessineVecteur
 * @author Benjamin CHAINTREUIL
 *
 */

class DessineVecteur {
	
	  /**
	   * Méthode pour l'affichage d'un vecteur à l'écran
	   * @param g : Objet Graphics
	   * @param v : Le vecteur à afficher
	   * @param showPosition : Booléen permettant de définir l'affichage, ou non, des coordonnées du vecteur à l'écran
	   */

	  static void dessine(Graphics g, Vecteur v, boolean showPosition) {
		  g.setColor(Color.blue);
		  g.drawLine(0, 0, (int)v.x, (int)v.y);
		  g.fillOval((int)v.x - 2, (int)v.y - 2, 4, 4);
		  if (showPosition)
				FrameHelper.printCenterX(g, v.toString(), (int)v.x, (int)v.y);
	  }

	  public static void main(String[] args) {
	    new frame.FrameHelper(200, 200, true).draw(g -> {
	      Vecteur v = new Vecteur(30, 40);
	      dessine(g, v, true);
	      FrameHelper.print(g, "Length : " + v.length(), -100, 90);
	    });
	  }
}
