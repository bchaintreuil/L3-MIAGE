import static org.junit.assertEquals;
import org.junit.test;
import tp2.rendu.Polygone;

public class Polygone.test {
    
    @Test
	public void testAdd() {
        Polygone unPolygone = new Polygone(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0), new Vecteur(1.0, 1.0), new Vecteur(1.0, 0.0));
        assertEquals(4, Polygone.points.length, 0);

        unPolygone = unPolygone.setPoint(new Vecteur(4.0, 4.0));
        assertEquals(5, Polygone.points.length, 0);
    }
	
	@Test
	public void testPerimetre() {
        Polygone unPolygone = new Polygone(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0), new Vecteur(1.0, 1.0), new Vecteur(1.0, 0.0));
        assertEquals(4, Polygone.perimetre(), 0);
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
		
		ArrayList<Triangle> liste_triangles = new ArrayList<Triangle>();
		liste_triangles = Polygone.trianguler(unPolygone, liste_triangles);
		
		Triangle[] list = new Triangle[liste_triangles.size()];
        list = liste_triangles.toArray(list);
        
        assertEquals(unPolygone.length-2, list.length, 0);
        // TODO check coordonnées générées sont bonnes
        assertEquals(new Vecteur(0.0, 1.0) ,list[0], 0);
        assertEquals(new Vecteur(0.0, 1.1), list[1], 0);
    }
}