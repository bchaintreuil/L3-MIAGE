/**
 * 
 */
package vector;

/**
 * @author benchai
 *
 */

public class Triangle {
	Point A, B, C;
	Vector AB, AC, BC;
	
	
	Triangle(Point A, Point B, Point C) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.AB = new Vector(B.x - A.x, B.y - A.y);
		this.AC = new Vector(C.x - A.x, C.y - A.y);
		this.BC = new Vector(C.x - B.x, C.y - B.y);
			
	}
	
	public double perimeter() {
		return AB.norm() + AC.norm() + BC.norm();
	}
	
	public Vector barycenter() {
		return Vector.add(Vector.add(Vector.div(AB, 3), Vector.div(AC, 3)), Vector.div(BC, 3));
	}
	
	public static void main(String[] args) {
		  	Triangle test = new Triangle(new Point(0,0), new Point(1, 0), new Point(0, 1));
		  	System.out.println("Coordonnées du barycentre : (" + test.barycenter().x + ", " + test.barycenter().y + ")");
	}
	
}
