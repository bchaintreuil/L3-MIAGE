package tp1.tests;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import test.TestSignature;
import tp1.vecteur.Vecteur;

public class VecteurTest {
	Random generateur = new Random();
	
	static void testX(double expected, Vecteur v) {
		test("x", expected, v.x);
	}
	static void testY(double expected, Vecteur v) {
		test("y", expected, v.y);
	}
	static private void test(String dim, double expected, double actual) {
		assertEquals("wrong " + dim, expected, actual, 1e-4);		
	}
	@Test
	public void testVecteur() {
		TestSignature test = new TestSignature(Vecteur.class);
		test.publicConstructors(1);
		test.publicConstructor(double.class, double.class);
		
		Vecteur v = new Vecteur(3, 4);
		testX(3, v);
		testY(4, v);
	}

	@Test
	public void testLength() {
		Vecteur v = new Vecteur(3, 4);
		assertEquals(v.length(), 5, 1e-4);
	}

	private int generateRandomInteger(int min, int max) {
		return generateur.nextInt(max - min) + min;
	}
	private Vecteur generateRandomVecteur(int min, int max) {
		int x = generateRandomInteger(min, max);
		int y = generateRandomInteger(min, max);
		return new Vecteur(x, y);
	}
	@Test
	public void testTranspose() {
		Vecteur v = generateRandomVecteur(-500, 500);
		Vecteur t = v.transpose();
		testX(v.y, t);
		testY(v.x, t);
	}

	@Test
	public void testOppose() {
		Vecteur v = generateRandomVecteur(-500, 500);
		Vecteur minusV = v.opposé();
		testX(-v.x, minusV);
		testY(-v.y, minusV);
	}
	
	
	@Test
	public void testMultK() {
		Vecteur v = generateRandomVecteur(-500, 500);
		int k = generateRandomInteger(-10, 10);
		Vecteur vk = v.multK(k);
		
		testX(v.x * k, vk);
		testY(v.y * k, vk);
	}

	@Test
	public void testAdd() {
		Vecteur v1 = generateRandomVecteur(-500, 500);
		Vecteur v2 = generateRandomVecteur(-500, 500);
		Vecteur v = Vecteur.add(v1, v2);
		testX(v1.x + v2.x, v);
		testY(v1.y + v2.y, v);
	}

	@Test
	public void testSub() {
		Vecteur v1 = generateRandomVecteur(-500, 500);
		Vecteur v2 = generateRandomVecteur(-500, 500);
		Vecteur v = Vecteur.sub(v1, v2);
		testX(v1.x - v2.x, v);
		testY(v1.y - v2.y, v);
	}

	@Test
	public void testProduitScalaire1() {
		Vecteur v = generateRandomVecteur(-500, 500);
		double p = Vecteur.produitScalaire(v, v);
		double sqrLength = v.length() * v.length();
		assertEquals("v.v=||v||^2", sqrLength, p, 1e-4);
	}

	@Test
	public void testProduitScalaire2() {
		Vecteur v = generateRandomVecteur(-500, 500);
		// rotate by 90 degrees
		Vecteur vR = new Vecteur(-v.y, v.x);
		double p = Vecteur.produitScalaire(v, vR);
		assertEquals("v.vT = 0 iif v and vT are orthogonal", 0, p, 1e-4);
	}
	
	@Test
	public void testProduitVectoriel1() {
		Vecteur v = generateRandomVecteur(-500, 500);
		double p = Vecteur.produitVectoriel(v, v);
		assertEquals("v.v = 0", 0, p, 1e-4);
	}

	
	@Test
	public void testProduitVectoriel2() {
		Vecteur v = generateRandomVecteur(-500, 500);
		if (v.x == 0 && v.y == 0) v.x = 1; // avoid length 0
		// rotate by 90 degrees
		Vecteur vR = new Vecteur(-v.y, v.x);
		double p = Vecteur.produitVectoriel(v, vR) / v.length() / vR.length();
		assertEquals(1, p, 1e-4);
	}
	@Test
	public void testToString() {
		Vecteur v = new Vecteur(3, 4);
		String s = v.toString();
		assertEquals("<3.0, 4.0>", s);
	}
}
