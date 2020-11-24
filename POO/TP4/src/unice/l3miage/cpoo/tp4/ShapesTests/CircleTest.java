package unice.l3miage.cpoo.tp4.ShapesTests;

import org.junit.Test;
import java.lang.Math;
import unice.l3miage.cpoo.tp4.Shape.Circle;
import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Vecteur;

import static org.junit.Assert.assertEquals;

public class CircleTest {
    @Test
    public void testAire() {
        Circle unCercle = new Circle(new Vecteur(0.0, 0.0), 5);
        assertEquals(78.54, unCercle.aire(), 10.e-1);
    }

    @Test
    public void testPerimetre() {
        Circle unCercle = new Circle(new Vecteur(0.0, 0.0), 5);
        assertEquals(31.416, unCercle.perimètre(), 10.e-1);
    }
}
