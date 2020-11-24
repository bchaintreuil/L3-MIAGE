package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Circle extends Shape implements iToPolygone {
    // Fields
    private Vecteur center;
    private double radius;

    // Constructor
    public Circle(Vecteur center, double radius) throws RuntimeException { // TODO : à tester
        if(center.dimension() != 2 || radius <= 0) {
            throw new RuntimeException("Vecteur de dimension != 2 ou rayon nul/négatif !");
        } else {
            this.center = center;
            this.radius = radius;
        }
    }

    // Getters & Setters
    public double getRadius() { return this.radius; }
    public Vecteur getCenter() { return this.center; };

    // Methods
    public double aire() { return Math.PI * Math.pow(radius, 2); }
    public double perimètre() { return Math.PI * 2 * radius; }

    public Polygone toPolygone() {
        ArrayList<Vecteur> points = new ArrayList<Vecteur>();
        IntStream intervalAngle = IntStream.range(0, 360).filter(x -> x % 10 == 0);

        intervalAngle.forEach(angle -> {
            double x = center.get(0) + radius * Math.cos(Math.toRadians(angle));
            double y = center.get(1) + radius * Math.sin(Math.toRadians(angle));

            Vecteur point = new Vecteur(2, x, y);
            points.add(point);
        });

        return new Polygone(points.toArray(new Vecteur[points.size()]));
    }
}
