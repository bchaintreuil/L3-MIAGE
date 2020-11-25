package unice.l3miage.cpoo.tp4.ShapesTests;

import org.junit.Test;
import unice.l3miage.cpoo.tp4.Shape.Triangle;
import unice.l3miage.cpoo.tp4.Vecteur;

import static org.junit.Assert.assertEquals;

public class TriangleTest {
    @Test
    public void testPerimetre() {
        Triangle unTriangle = new Triangle(new Vecteur(0.0, 0.0), new Vecteur(1.0, 0.0),new Vecteur(0.0, 1.0));
        assertEquals(3, unTriangle.perimètre(),0);
    }

    @Test
    public void testAire() {
        Triangle unTriangle = new Triangle(new Vecteur(0.0, 0.0), new Vecteur(1.0, 0.0),new Vecteur(0.0, 1.0));
        assertEquals(0.433012701892, unTriangle.aire(),10e-2);
    }

    @Test
    public void testBarycentre() {
        Triangle unTriangle = new Triangle(new Vecteur(0.0, 0.0), new Vecteur(1.0, 0.0),new Vecteur(0.0, 1.0));
        var barycentre = unTriangle.barycentre();

        assertEquals(0, barycentre.get(0), 0);
        assertEquals(0, barycentre.get(1), 0);
    }
}
