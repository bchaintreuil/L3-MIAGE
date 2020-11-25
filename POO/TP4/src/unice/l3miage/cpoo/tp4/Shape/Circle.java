package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Circle extends Shape implements iToPolygone {
    // Constructor fields
    private final Vecteur center;
    private final double radius;

    /**
     * Constructeur de la classe Cercle
     *
     * @param center : Vecteur-point du centre du cercle
     * @param radius : Radius du cercle
     */
    public Circle(Vecteur center, double radius) throws RuntimeException {
        if (center.dimension() != 2 || radius <= 0) {
            throw new RuntimeException("Vecteur de dimension != 2 ou rayon nul/négatif !");
        } else {
            this.center = center;
            this.radius = radius;
        }
    }

    // Methods

    /**
     * Détermination de l'aire du cercle
     *
     * @return Aire du cercle
     */
    public double aire() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * Détermination du périmètre du cercle
     *
     * @return Périmètre du cercle
     */
    public double perimètre() {
        return Math.PI * 2 * radius;
    }

    /**
     * Convertir un objet cercle en objet Polygone
     *
     * @return L'objet Polygone crée à partir du cercle
     */
    public Polygone toPolygone() {
        ArrayList<Vecteur> points = new ArrayList<>();
        IntStream intervalAngle = IntStream.range(0, 360).filter(x -> x % 10 == 0);

        intervalAngle.forEach(angle -> {
            double x = center.get(0) + radius * Math.cos(Math.toRadians(angle));
            double y = center.get(1) + radius * Math.sin(Math.toRadians(angle));

            Vecteur point = new Vecteur(2, x, y);
            points.add(point);
        });

        return new Polygone(points.toArray(new Vecteur[0]));
    }

    // Getters
    public double getRadius() {
        return this.radius;
    }

    public Vecteur getCenter() {
        return this.center;
    }
}
