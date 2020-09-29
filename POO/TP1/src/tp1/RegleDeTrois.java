package tp1;

public class RegleDeTrois {
	double m1, m2;
	public RegleDeTrois(double m1, double m2) {
		this.m1 = m1;
		this.m2 = m2;
	}
	public double de1vers2(double userInput) {
		return (m2/m1)*userInput;
	}
	public double de2vers1(double userInput) {
		return (m1/m2)*userInput;
	}
}
