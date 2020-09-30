package vector;

/**
 * @author benchai
 *
 */

public class Vector {
	double x, y;
	
	Vector (double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double norm() {
		return Math.sqrt(x*x + y*y);
	}
	
	public Vector multK(double k) {
		return new Vector(k*x, k*y);
	}
	
	public Vector transp() {
		return new Vector(y, x);
	}
	
	public Vector opp() {
		return new Vector(-x, -y);
	}
	
	public static Vector add(Vector v1, Vector v2) {
		return new Vector(v1.x + v2.x, v1.y + v2.y);
	}
	
	public static Vector sub(Vector v1, Vector v2) {
		return new Vector(Vector.add(v1, v2.opp()).x, Vector.add(v1, v2.opp()).y);
	}
	
	public static Vector div(Vector v1, double k) {
		return new Vector(v1.x/k, v1.y/k);
	}
	
	public String print() {
		return "(x : " + this.x + ", y : " + this.y + ")";
	}
}

