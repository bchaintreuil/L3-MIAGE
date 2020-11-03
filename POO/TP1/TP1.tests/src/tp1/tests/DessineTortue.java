package tp1.tests;

import frame.FrameHelper;
import tp1.turtle.Tortue;
import tp1.vecteur.Vecteur;

class DessineTortue {
	public static void main(String[] args) {
		new FrameHelper(400, 400, true).draw(g -> {
			Tortue léa = new Tortue();
			léa.setPosition(-50, -50);
			for (int i = 0; i < 4; i++) {
				léa.avance(100);
				DessineVecteur.dessine(g, new Vecteur(léa.getX(), léa.getY()), true);
				léa.droite(90);
			}
		});
	}

}
