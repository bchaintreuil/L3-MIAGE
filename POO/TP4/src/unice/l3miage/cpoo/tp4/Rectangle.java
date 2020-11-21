package unice.l3miage.cpoo.tp4;

public class Rectangle extends Shape {

    public Vecteur OA, OB, OC, OD;

    /**
     * Constructeur de la classe
     * @param OA : Vecteur-point d'un sommet du triangle
     * @param OB : Vecteur-point d'un sommet du triangle
     * @param OC : Vecteur-point d'un sommet du triangle
     */

    public Rectangle(Vecteur OA, Vecteur OB, Vecteur OC, Vecteur OD) {
        this.OA = OA;
        this.OB = OB;
        this.OC = OC;
        this.OD = OD;
    }

    /**
     * Détermination de l'aire du rectangle
     * @return Aire du rectangle
     */

    @Override
    public double aire() {
        return Vecteur.sub(OB, OA).length()*Vecteur.sub(OC, OA).length();
    }

    /**
     * Détermination du barycentre du rectangle
     * @return Vecteur-point du rectangle
     */

    @Override
    public Vecteur barycentre() {
        Vecteur foo = Vecteur.add(getPoints());
        return foo.multK(1.0/ getPoints().length);
    }
}
