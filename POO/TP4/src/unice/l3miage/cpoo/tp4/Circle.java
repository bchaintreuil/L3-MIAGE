package unice.l3miage.cpoo.tp4;

public class Circle extends Shape {
    private Vecteur center;
    private double radius;

    public double aire() {
        return Math.pi * Math.pow(radius, 2);
    }

    public Vecteur getCenter() {
        return this.barycentre();
    }

    public Vecteur barycentre(){
        return this.center;
    }

    public double getRadius() {
        return this.radius;
    }
}
