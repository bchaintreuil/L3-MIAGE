package unice.l3miage.cpoo.tp4.ShapesTests;

import org.junit.Test;
import unice.l3miage.cpoo.tp4.Shape.Line;
import unice.l3miage.cpoo.tp4.Vecteur;

import static org.junit.Assert.assertEquals;

public class LineTest {

    @Test
    public void testLongueur(){
        Line uneLigne = new Line(new Vecteur(0.0, 0.0), new Vecteur(0.0, 1.0));
        assertEquals(1, uneLigne.length(), 10.e-1);
    }
}
