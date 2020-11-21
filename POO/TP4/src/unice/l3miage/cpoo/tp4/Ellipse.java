package unice.l3miage.cpoo.tp4;

public class Ellipse extends Shape {
    private Vecteur center;
    private double radiusX;
    private double radiusY;

    public double aire() {
        return Math.pi * radiusX * radiusY;
    }

    public Vecteur barycentre(){
        return this.center;
    }

    public Vecteur getCenter() {
        return this.barycentre();
    }

    public double getRadiusX() {
        return this.radiusX;
    }

    public double getRadiusY() {
        return this.radiusY;
    }
}
