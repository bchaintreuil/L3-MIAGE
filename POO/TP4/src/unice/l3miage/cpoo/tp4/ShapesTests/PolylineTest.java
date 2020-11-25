package unice.l3miage.cpoo.tp4.ShapesTests;

import org.junit.Test;
import unice.l3miage.cpoo.tp4.Shape.Polyline;
import unice.l3miage.cpoo.tp4.Vecteur;
import static org.junit.Assert.assertEquals;

public class PolylineTest {

    @Test
    public void testLongueur(){
        Polyline unePolyligne = new Polyline(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0));
        assertEquals(1, unePolyligne.length(), 10.e-1);
    }
}
