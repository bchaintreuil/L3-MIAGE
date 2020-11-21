package unice.l3miage.cpoo.tp4;

/**
 * Classe Vecteur
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */

public class Vecteur {
    // Constructor and constructor variable
    private double coords[];


    public Vecteur(double... coords) {
        this(coords.length, coords);
    }

    public Vecteur(int n, double... coords) {
        this.coords = new double[n];
        if (n > coords.length) {
            System.arraycopy(coords, 0, this.coords, 0, coords.length);
        } else {
            System.arraycopy(coords, 0, this.coords, 0, n);
        }
    }

    // Getters and setters
    public double get(int i) throws RuntimeException{
        if (i > this.dimension()) {
            throw new RuntimeException("i > nbr de composantes");
        } else {
            return this.coords[i];
        }
    }

    /*
     * Methods
     */

    // Calcul et renvoi la norme du vecteur
    public double length() {
        double length = 0;
        for(double coord : this.coords) {
            length += Math.pow(coord, 2);
        }
        return Math.sqrt(length);
    }

    // Renvoi la dimension du vecteur
    public int dimension() {
        return this.coords.length;
    }

    // Calcul et renvoi le vecteur opposé
    public Vecteur opposé() {
        double foo[] = new double[this.dimension()];
        for(int i = 0; i < this.dimension(); i++) {
            foo[i] -= this.coords[i];
        }
        return new Vecteur(foo);
    }

    // Renvoi le vecteur résultant de la multiplication par un scalaire k
    public Vecteur multK(double k) {
        double bar[] = new double[this.dimension()];
        for(int i = 0; i < this.dimension(); i++) {
            bar[i] = this.coords[i]*k;
        }
        return new Vecteur(bar);
    }

    // Renvoi le vecteur transposé
    public Vecteur transpose() throws RuntimeException{
        if (this.dimension() == 2) {
            return new Vecteur(this.coords[1],  this.coords[0]);
        }
        throw new RuntimeException("dim != 2");
    }

    // Méthode permettant l'addition de n vecteurs
    public static Vecteur add(Vecteur...vecteurs) throws RuntimeException {
        int dim = vecteurs[0].dimension();
        for(Vecteur vecteur:vecteurs) {
            if (vecteur.dimension() != dim) {
                throw new RuntimeException("Dim différentes !");
            }
        }

        double coords[] = new double[dim];
        for (Vecteur vecteur: vecteurs) {
            for(int i = 0; i < dim; i++) {
                coords[i] += vecteur.coords[i];
            }
        }

        return new Vecteur(coords);
    }

    // Méthode permettant de soustraire n vecteurs
    public static Vecteur sub(Vecteur...vecteurs) {
        Vecteur vect[] = new Vecteur[vecteurs.length];
        for(int i = 0; i < vecteurs.length; i++) {
            if (i > 0) {
                vect[i] = vecteurs[i].opposé();
                continue;
            }
            vect[0] = vecteurs[0];
        }
        return Vecteur.add(vect);
    }

    // Méthode permettant de renvoyer le résultat du "produit scalaire" de n vecteurs
    public static double produitScalaire(Vecteur...vecteurs) throws RuntimeException {
        int dim = vecteurs[0].dimension();
        for(Vecteur vecteur:vecteurs) {
            if (vecteur.dimension() != dim) {
                throw new RuntimeException("Dim différentes !");
            }
        }

        double foo[] = new double[dim];
        System.arraycopy(foo, 0, vecteurs[0].coords, 0, vecteurs[0].dimension());
        for(int i = 1; i < vecteurs.length; i++) {
            for(double coord: vecteurs[i].coords) {
                foo[i] *= coord;
            }
        }
        double bar = 0;
        for (double i : foo) {
            bar += i;
        }
        return bar;
    }

    // Renvoi la norme du produit vectoriel entre deux vecteur v1 et v2 de dimension 3
    public static Vecteur produitVectoriel(Vecteur v1, Vecteur v2) throws RuntimeException {
        if (v1.dimension() == 3 && v2.dimension() == 3) {
            return new Vecteur(v1.get(1)*v2.get(2)-v1.get(2)*v2.get(1), v1.get(2)*v2.get(0)-v1.get(0)*v2.get(2), v1.get(0)*v2.get(1)-v1.get(1)*v2.get(0));
        }
        throw new RuntimeException("Dim != 3");
    }

    // Affiche les coordonnées du vecteur à l'écran
    public void print() {
        System.out.print("<");
        for(int i = 0; i < this.coords.length; i++) {
            if (i <= this.coords.length-2) {
                System.out.print(this.coords[i] + ", ");

            } else {
                System.out.print(this.coords[i]);
            }
        }
        System.out.println(">");
    }

}
