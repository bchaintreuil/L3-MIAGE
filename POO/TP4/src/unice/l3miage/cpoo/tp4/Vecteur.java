package unice.l3miage.cpoo.tp4;

/**
 * Classe Vecteur
 *
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */

public class Vecteur {
    // Constructor and constructor vars
    private final double[] coords;

    // Constructor

    /*
     * Constructeur de la classe Vecteur prenant en compte un tableau de coordoonnées
     * @param coords : Liste des coordonnées du Vecteur
     */
    public Vecteur(double... coords) {
        this(coords.length, coords);
    }

    /*
     * Constructeur de la classe Vecteur prenant en compte un tableau de coordoonnées et le nombre de points du Vecteur
     * @param n : Nombre de points du vecteur
     * @param coords : Liste des coordonnées du Vecteur
     */
    public Vecteur(int n, double... coords) {
        this.coords = new double[n];
        System.arraycopy(coords, 0, this.coords, 0, Math.min(n, coords.length));
    }

    /*
     * Methods
     */

    /**
     * Méthode permettant l'addition de n vecteurs
     *
     * @param vecteurs : Liste des vecteurs à ajouter
     * @return Vecteur résultant de l'addition des vecteurs données en paramètres
     */
    public static Vecteur add(Vecteur... vecteurs) throws RuntimeException {
        int dim = vecteurs[0].dimension();
        for (Vecteur vecteur : vecteurs) {
            if (vecteur.dimension() != dim) {
                throw new RuntimeException("Dim différentes !");
            }
        }

        double[] coords = new double[dim];
        for (Vecteur vecteur : vecteurs) {
            for (int i = 0; i < dim; i++) {
                coords[i] += vecteur.coords[i];
            }
        }

        return new Vecteur(coords);
    }

    /**
     * Méthode permettant de soustraire n vecteurs
     *
     * @param vecteurs : Liste des vecteurs à soustraire
     * @return Vecteur résultant de la soustraction des vecteurs donnés en paramètre
     */
    //
    public static Vecteur sub(Vecteur... vecteurs) {
        Vecteur[] vect = new Vecteur[vecteurs.length];
        for (int i = 0; i < vecteurs.length; i++) {
            if (i > 0) {
                vect[i] = vecteurs[i].opposé();
                continue;
            }
            vect[0] = vecteurs[0];
        }
        return Vecteur.add(vect);
    }

    /**
     * Méthode permettant de renvoyer le résultat du "produit scalaire" de n vecteurs
     *
     * @param vecteurs : Vecteurs utilisés pour le produit scalaire
     * @return Produit scalaire des n vecteurs
     */
    public static double produitScalaire(Vecteur... vecteurs) throws RuntimeException {
        int dim = vecteurs[0].dimension();
        for (Vecteur vecteur : vecteurs) {
            if (vecteur.dimension() != dim) {
                throw new RuntimeException("Dim différentes !");
            }
        }

        double[] foo = new double[dim];
        System.arraycopy(foo, 0, vecteurs[0].coords, 0, vecteurs[0].dimension());
        for (int i = 1; i < vecteurs.length; i++) {
            for (double coord : vecteurs[i].coords) {
                foo[i] *= coord;
            }
        }
        double bar = 0;
        for (double i : foo) {
            bar += i;
        }
        return bar;
    }

    /**
     * Renvoi la norme du produit vectoriel entre deux vecteur v1 et v2 de dimension 3
     *
     * @param v1 : Premier vecteur utilisé pour le calcul
     * @param v2 : Deuxième vecteur utilisé pour le calcul
     * @return Vecteur résultant du produit vectoriel
     */
    public static Vecteur produitVectoriel(Vecteur v1, Vecteur v2) throws RuntimeException {
        if (v1.dimension() == 3 && v2.dimension() == 3) {
            return new Vecteur(v1.get(1) * v2.get(2) - v1.get(2) * v2.get(1), v1.get(2) * v2.get(0) - v1.get(0) * v2.get(2), v1.get(0) * v2.get(1) - v1.get(1) * v2.get(0));
        }
        throw new RuntimeException("Dim != 3");
    }

    /**
     * Calcul et renvoi la norme du vecteur
     *
     * @return Norme du vecteur
     */
    public double length() {
        double length = 0;
        for (double coord : this.coords) {
            length += Math.pow(coord, 2);
        }
        return Math.sqrt(length);
    }

    /**
     * Calcul et renvoi la dimension du vecteur
     *
     * @return Dimension du vecteur
     */
    public int dimension() {
        return this.coords.length;
    }

    /**
     * Renvoi le vecteur transposé
     *
     * @return Vecteur transposé
     */
    public Vecteur transpose() throws RuntimeException {
        if (this.dimension() == 2) {
            return new Vecteur(this.coords[1], this.coords[0]);
        }
        throw new RuntimeException("dim != 2");
    }

    /**
     * Calcul et renvoi le vecteur opposé
     *
     * @return Vecteur opposé
     */
    public Vecteur opposé() {
        double[] foo = new double[this.dimension()];
        for (int i = 0; i < this.dimension(); i++) {
            foo[i] -= this.coords[i];
        }
        return new Vecteur(foo);
    }

    /**
     * Renvoi le vecteur résultant de la multiplication par un scalaire k
     *
     * @param k : Scalaire de la multiplication
     * @return Vecteur résultant de la multiplication
     */
    public Vecteur multK(double k) {
        double[] bar = new double[this.dimension()];
        for (int i = 0; i < this.dimension(); i++) {
            bar[i] = this.coords[i] * k;
        }
        return new Vecteur(bar);
    }

    /**
     * Affiche les coordonnées du vecteur à l'écran
     */
    public void print() {
        System.out.print("<");
        for (int i = 0; i < this.coords.length; i++) {
            if (i <= this.coords.length - 2) {
                System.out.print(this.coords[i] + ", ");

            } else {
                System.out.print(this.coords[i]);
            }
        }
        System.out.println(">");
    }

    // Getters and setters

    /**
     * Renvoie la composante i du Vecteur
     *
     * @return Valeur de la composante i
     */
    public double get(int i) throws RuntimeException {
        if (i > this.dimension()) {
            throw new RuntimeException("i > nbr de composantes");
        } else {
            return this.coords[i];
        }
    }
}
