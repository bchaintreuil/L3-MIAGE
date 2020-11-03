package tp2.tests;

/**
 * Classe de test Polygone
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import tp2.rendu.Polygone;
import tp2.rendu.Triangle;
import tp2.rendu.Vecteur;

public class PolygoneTest {
    
    @Test
	public void testAdd() {
        Polygone unPolygone = new Polygone(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0), new Vecteur(1.0, 1.0), new Vecteur(1.0, 0.0));
        assertEquals(4,unPolygone.nbrPoints(), 0);
        
        // On ajoute un point
        unPolygone = unPolygone.setPoint(new Vecteur(4.0, 4.0));
        assertEquals(5, unPolygone.nbrPoints(), 0);
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
		Triangle[] liste_triangles = unPolygone.trianguler();
		
        assertEquals(unPolygone.nbrPoints() - 2, liste_triangles.length, 0);
        
        // Triangle 1
        assertEquals(0.0 ,liste_triangles[0].OA.get(0), 0);
        assertEquals(0.0 ,liste_triangles[0].OA.get(1), 0);
        assertEquals(0.0 ,liste_triangles[0].OB.get(0), 0);
        assertEquals(1.0 ,liste_triangles[0].OB.get(1), 0);
        assertEquals(1.0 ,liste_triangles[0].OC.get(0), 0);
        assertEquals(0.0 ,liste_triangles[0].OC.get(1), 0);
        
        // Triangle 2
        assertEquals(0.0 ,liste_triangles[1].OA.get(0), 0);
        assertEquals(1.0 ,liste_triangles[1].OA.get(1), 0);
        assertEquals(1.0 ,liste_triangles[1].OB.get(0), 0);
        assertEquals(1.0 ,liste_triangles[1].OB.get(1), 0);
        assertEquals(1.0 ,liste_triangles[1].OC.get(0), 0);
        assertEquals(0.0 ,liste_triangles[1].OC.get(1), 0);
    }
}