package vector;

import frame.FrameHelper; 
import java.awt.Graphics;
import java.awt.Color;

class DessineVecteur {
	  static void dessine(Graphics g, Vector v, boolean showPosition) {
		  g.setColor(Color.blue);
		  g.drawLine(0, 0, (int)v.x, (int)v.y);
		  g.fillOval((int)v.x - 2, (int)v.y - 2, 4, 4);
		  if (showPosition)
				FrameHelper.printCenterX(g, v.print(), (int)v.x, (int)v.y);
	  }

	  public static void main(String[] args) {
	    new frame.FrameHelper(200, 200, true).draw(g -> {
	      Vector v = new Vector(30, 40);
	      dessine(g, v, true);
	      FrameHelper.print(g, "Length : " + v.norm(), -100, 90);
	    });
	  }
}
