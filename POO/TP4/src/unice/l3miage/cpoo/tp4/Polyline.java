package unice.l3miage.cpoo.tp4;

public class Polyline {
    // Constructor and constructor variable
    private Vecteur[] points;

    public Polyline(Vecteur... points) {
        this.points = new Vecteur[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    // TODO: getters and setters
}
