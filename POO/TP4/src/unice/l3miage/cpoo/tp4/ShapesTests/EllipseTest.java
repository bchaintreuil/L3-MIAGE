package unice.l3miage.cpoo.tp4.ShapesTests;

import org.junit.Test;
import unice.l3miage.cpoo.tp4.Shape.Ellipse;
import unice.l3miage.cpoo.tp4.Vecteur;

import static org.junit.Assert.assertEquals;

public class EllipseTest {

    @Test
    public void testAire() {
        Ellipse uneEllipse = new Ellipse(new Vecteur(0.0, 0.0), 10, 8);
        assertEquals(251.32741, uneEllipse.aire(), 10.e-1);
    }

    @Test
    // Test fait avec la formule de Ramanujan
    public void testPerimetre() {
        Ellipse uneEllipse = new Ellipse(new Vecteur(0.0, 0.0), 10, 8);
        assertEquals(56.723336, uneEllipse.perimètre(), 10.e-1);
    }
}
