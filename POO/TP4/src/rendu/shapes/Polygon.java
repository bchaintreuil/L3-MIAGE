package rendu.shapes;

import java.util.ArrayList;

import rendu.interfaces.Shapes;
import tp3.v2.*;

public class Polygon implements Shapes{
	
	@Override
	public Triangle[] trianguler() {
		ArrayList<Triangle> liste_triangles = new ArrayList<Triangle>();
		liste_triangles = tp3.v2.Polygone.trianguler(liste_triangles);
		
		Triangle[] triangles = new Triangle[liste_triangles.size()];
		triangles = liste_triangles.toArray(triangles);
		return triangles;
	}
	
	
	
	
}
