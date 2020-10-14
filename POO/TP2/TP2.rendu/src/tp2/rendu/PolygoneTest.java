package tp2.rendu;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PolygoneTest {
    
    @Test
	public void testAdd() {
        Polygone unPolygone = new Polygone(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0), new Vecteur(1.0, 1.0), new Vecteur(1.0, 0.0));
        assertEquals(4,unPolygone.length(), 0);
        unPolygone = unPolygone.setPoint(new Vecteur(4.0, 4.0));
        assertEquals(5, unPolygone.length(), 0);
    }
	
	@Test
	public void testPerimetre() {
        Polygone unPolygone = new Polygone(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0), new Vecteur(1.0, 1.0), new Vecteur(1.0, 0.0));
        assertEquals(4, unPolygone.perimetre(), 0);
	}
	
	@Test
	public void testBarycentre() {
        Polygone unPolygone = new Polygone(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0), new Vecteur(1.0, 1.0), new Vecteur(1.0, 0.0));
        var barycentre = unPolygone.barycentre();
        
        assertEquals(0.5, barycentre.get(0), 0);
        assertEquals(0.5, barycentre.get(1), 0);
    }

    @Test
	public void testTrianguler() {
        Polygone unPolygone = new Polygone(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0), new Vecteur(1.0, 1.0), new Vecteur(1.0, 0.0));
		//Polygone unPolygone = new Polygone(new Vecteur(0.0,0.0,0.0));
		Triangle[] liste_triangles = unPolygone.trianguler();
		
        assertEquals(unPolygone.length()-2, liste_triangles.length, 0);
        // TODO check coordonnées générées sont bonnes
        assertEquals(new Vecteur(0.0, 1.0) ,liste_triangles[0]);
        assertEquals(new Vecteur(0.0, 1.1), liste_triangles[1]);
    }
}