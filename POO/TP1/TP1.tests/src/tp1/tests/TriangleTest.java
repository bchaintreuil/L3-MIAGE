package tp1.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import test.TestSignature;
import tp1.triangle.Triangle;
import tp1.vecteur.Vecteur;

public class TriangleTest {
	@Test
	public void testVecteur() {
		TestSignature test = new TestSignature(Triangle.class);
		test.publicConstructors(1);
		test.publicConstructor(Vecteur.class, Vecteur.class, Vecteur.class);		
	}
	
	@Test
	public void testPerimetre() {
		Triangle triangle = new Triangle(new Vecteur(0, 0),
				new Vecteur(3, 0), new Vecteur(0, 4));
		assertEquals(3 + 4 + 5, triangle.perimetre(), 1e-4);
	}
	
	@Test
	public void testBarycentre() {
		Triangle triangle = new Triangle(new Vecteur(-1, -1),
				new Vecteur(1, -1), new Vecteur(0, 2));
		Vecteur barycentre = triangle.barycentre();
		VecteurTest.testX(0.0, barycentre);
		VecteurTest.testY(0.0, barycentre);
	}
}
