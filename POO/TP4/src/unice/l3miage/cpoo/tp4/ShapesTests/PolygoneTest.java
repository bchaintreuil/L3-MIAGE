package unice.l3miage.cpoo.tp4.ShapesTests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Shape.Triangle;
import unice.l3miage.cpoo.tp4.Vecteur;

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
        assertEquals(4, unPolygone.perimètre(), 0);
    }

    @Test
    public void testAire() {
        Polygone unPolygone = new Polygone(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0), new Vecteur(1.0, 1.0), new Vecteur(1.0, 0.0));
        assertEquals(1, unPolygone.aire(), 10.e-1);
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
