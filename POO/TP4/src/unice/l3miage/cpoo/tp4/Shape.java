package unice.l3miage.cpoo.tp4;

abstract class Shape {
    protected Vecteur[] points;

    /**
     * Calcul de l'aire de la forme donnée
     * @return L'aire
     */

    public abstract double aire();

    /**
     * Calcul du périmètre de la forme donnée
     * @return Le périmètre
     */

    public double perimeter(){
        double perimetre = 0;
        for(int i=0; i<this.points.length; i++){
            if (i < this.points.length - 1) {
                perimetre += Vecteur.add(this.points[i], this.points[i+1].opposé()).length();
            } else {
                perimetre += Vecteur.add(this.points[i], this.points[0].opposé()).length();
            }
        }
        return perimetre;
    }

    /**
     * Calcul du barycentre de la forme donnée
     * @return Le barycentre
     */

    public abstract Vecteur barycentre();

    /**
     * Renvoi de la liste des points de la forme donnée, sous forme de vecteurs
     * @return La liste des points
     */

    public Vecteur[] getPoints() {
        Vecteur[] foo = new Vecteur[this.points.length];
        System.arraycopy(this.points, 0, foo, 0, this.points.length);
        return foo;
    }
}
