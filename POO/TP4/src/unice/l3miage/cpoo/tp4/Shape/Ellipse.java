package unice.l3miage.cpoo.tp4.Shape;

import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Ellipse extends Shape implements iToPolygone {
    // Constructor variables
    private final Vecteur center;
    private final double radiusX;
    private final double radiusY;

    /**
     * Constructeur de la classe Ellipse
     * @param center : Vecteur-point du centre de l'ellipse
     * @param radiusX : Radius x de l'ellipse
     * @param radiusY : Radius y de l'ellipse
     */
    public Ellipse(Vecteur center, double radiusX, double radiusY) throws RuntimeException {
        if (center.dimension() != 2 || radiusX <= 0 || radiusY <= 0) {
            throw new RuntimeException("Vecteur de dimension != 2 ou rayon nul/négatif !");
        } else {
            this.center = center;
            this.radiusX = radiusX;
            this.radiusY = radiusY;
        }
    }

    // Methods

    /**
     * Calcul de l'aire de l'ellipse
     * @return L'aire de l'ellipse
     */
    public double aire() {
        return Math.PI * radiusX * radiusY;
    }

    /**
     * Calcul du périmètre de l'ellipse grâce à la formule de Ramanujan
     * @return Le périmètre de l'ellipse
     */
    public double perimètre() {
        return Math.PI * (3 * (this.radiusX + this.radiusY) - Math.sqrt((3 * radiusX + radiusY) * (radiusX + 3 * radiusY)));
    }

    /**
     * Convertir l'objet Ellipse en un objet Polygone
     * @return L'objet Polygone crée à partir de l'objet Ellipse
     */
    public Polygone toPolygone() {
        ArrayList<Vecteur> points = new ArrayList<>();
        IntStream intervalAngle = IntStream.range(0, 360).filter(x -> x % 10 == 0);

        intervalAngle.forEach(angle -> {
            double x = center.get(0) + radiusX * Math.cos(Math.toRadians(angle));
            double y = center.get(1) + radiusY * Math.sin(Math.toRadians(angle));

            Vecteur point = new Vecteur(2, x, y);
            points.add(point);
        });

        return new Polygone(points.toArray(new Vecteur[0]));
    }

    // Getters & Setters
    public Vecteur getCenter() {
        return this.center;
    }
    public double getRadiusX() {
        return this.radiusX;
    }
    public double getRadiusY() {
        return this.radiusY;
    }
}
