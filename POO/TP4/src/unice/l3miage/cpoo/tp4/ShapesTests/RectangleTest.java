package unice.l3miage.cpoo.tp4.ShapesTests;

import org.junit.Test;
import unice.l3miage.cpoo.tp4.Shape.Rectangle;
import unice.l3miage.cpoo.tp4.Vecteur;

import static org.junit.Assert.assertEquals;

public class RectangleTest {

    @Test
    public void testPerimetre() {
        Rectangle unRectangle = new Rectangle(new Vecteur(0.0, 0.0), 100, 200);
        assertEquals(600, unRectangle.perimètre(), 0);
    }

    @Test
    public void testAire() {
        Rectangle unRectangle = new Rectangle(new Vecteur(0.0, 0.0), 100, 200);
        assertEquals(20000, unRectangle.aire(), 0);
    }

    @Test
    public void testBarycentre() {
        Rectangle unRectangle = new Rectangle(new Vecteur(2, 0.0, 0.0), 1, 1);
        var barycentre = unRectangle.barycentre();

        assertEquals(0.5, barycentre.get(0), 0);
        assertEquals(0.5, barycentre.get(1), 0);
    }
}
