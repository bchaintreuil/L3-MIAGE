package tp1.tests;

import java.awt.Color;
import java.awt.Graphics;

import frame.FrameHelper;
import TP1.rendu.Vector;

class DessineVecteur {
	static void dessine(Graphics g, Vecteur v, boolean showPosition) {
		g.setColor(Color.blue);
		//		g.drawLine(0, 0, (int)v.x, (int)v.y);		
		g.fillOval((int)v.x - 2, (int)v.y - 2, 4, 4);

		if (showPosition)
			FrameHelper.printCenterX(g, v.toIntString(), (int)v.x, (int)v.y);
	}

	public static void main(String[] args) {
		new FrameHelper(200, 200, true).draw(g -> {
			Vecteur v = new Vecteur(30, 40);

			dessine(g, v, true);			
			FrameHelper.print(g, "Length : " + v.length(), -100, 90);
		});
	}
}
