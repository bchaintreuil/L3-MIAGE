package tp1.tests;

import java.awt.Color;
import java.awt.Graphics;

import frame.FrameHelper;
import frame.Paintable;
import tp1.vecteur.Vecteur;

class DessineVecteur1 implements Paintable {
	public void paint(Graphics g) {
		Vecteur v = new Vecteur(30, 40);
		
		dessine(g, v);
	}
	
	void dessine(Graphics g, Vecteur v) {
		g.setColor(Color.blue);
		g.drawLine(0, 0, (int)v.x, (int)v.y);		
		g.fillOval((int)v.x - 2, (int)v.y - 2, 4, 4);
		FrameHelper.print(g, v.toString(), (int)v.x, (int)v.y);
		FrameHelper.print(g, "Length : " + v.length(), -100, 90);
	}
	
	public static void main(String[] args) {
		new FrameHelper(200, 200, true).draw(new DessineVecteur1());
	}
}
