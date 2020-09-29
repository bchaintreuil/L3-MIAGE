package tp1;

public class Polynome {
	double a, b, c, x;
	
	public double calcul(double x) {
		return a * Math.pow(x, 2) + b * x + c;
	}
	
	Polynome(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;

	}

}
