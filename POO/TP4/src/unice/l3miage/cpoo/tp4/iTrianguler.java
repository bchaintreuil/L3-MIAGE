package unice.l3miage.cpoo.tp4;

import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Shape.Triangle;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public interface iTrianguler {
    public Vecteur getPoint(int i);
    public Vecteur[] getPoints();
    /*
     * Triangulation
     */

    // Renvoi l'indice du sommet le plus � gauche du polygone.
    default int sommet_gauche() {
        double x = this.getPoint(0).get(0);
        int k = 0;
        for(int i = 1; i < this.getPoints().length; i++) {
            if(this.getPoint(i).get(0) < x) {
                x = this.getPoint(i).get(0);
                k = i;
            }
        }
        return k;
    }

    // Retourne l'indice du sommet voisin � celui de l'indice en fonction du d�placement
    static int voisin_sommet(int nbrSommets, int indice, int dep) {
        int indiceVoisin = (indice + dep) % nbrSommets;
        if (indiceVoisin == -1) {
            indiceVoisin += nbrSommets;
        }
        return indiceVoisin;
    }

    // Renvoi la norme de la composante Z du produit vectoriel de POP1 et POPM
    static double produit_vect_Z (Vecteur P0, Vecteur P1, Vecteur M) {
        return (P1.get(0) - P0.get(0)) * (M.get(1) - P0.get(1)) - (P1.get(1) - P0.get(1)) * (M.get(0) - P0.get(0));
    }

    // Renvoi un bool�en traduisant de la pr�sence du point M dans le triangle d�limit� par P0, P1, P2
    static boolean point_dans_triangle(Vecteur P0, Vecteur P1, Vecteur P2, Vecteur M) {
        return produit_vect_Z(P0, P1, M) > 0 && produit_vect_Z(P1, P2, M) > 0 && produit_vect_Z(P2, P0, M) > 0;
    }

    // Renvoi l'indice du sommet du polygone appartenant au triangle P0, P1, P2 et qui est � la plus grande distance du c�t� P1P2
    default int indice_sommet_distance_max(Vecteur P0, Vecteur P1, Vecteur P2, int indice_P0, int indice_P1, int indice_P2) {
        int n = this.getPoints().length;
        double distance = 0.0;
        int k = -1;

        for(int i = 0; i < n; i++) {
            if (i != indice_P0 && i != indice_P1 && i != indice_P2) {
                Vecteur M = this.getPoint(i);
                if (point_dans_triangle(P0, P1, P2, M)) {
                    double d = Math.abs(produit_vect_Z(P1, P2, M));
                    if (d > distance) {
                        distance = d;
                        k = i;
                    }
                }
            }
        }
        return k;
    }

    // Renvoi un nouveau polygone constitu� des points compris entre l'indice iStart et iEnd
    default Polygone new_polygone(int iStart, int iEnd) {
        int n = this.getPoints().length;
        ArrayList<Vecteur> sommets = new ArrayList<Vecteur>();
        int i = iStart;
        while(i != iEnd) {
            sommets.add(this.getPoint(i));
            i = voisin_sommet(n, i, 1);
        }
        sommets.add(this.getPoint(iEnd));

        Vecteur[] s = new Vecteur[sommets.size()];
        s = sommets.toArray(s);
        return new Polygone(s);
    }

    // M�thodes pour trianguler un polygone
    default Triangle[] trianguler() {
        ArrayList<Triangle> liste_triangles = new ArrayList<Triangle>();
        liste_triangles = this.trianguler(liste_triangles);

        Triangle[] triangles = new Triangle[liste_triangles.size()];
        triangles = liste_triangles.toArray(triangles);
        return triangles;
    }

    default ArrayList<Triangle> trianguler(ArrayList<Triangle> liste_triangles) {
        int nbrSommets = this.getPoints().length;

        int indiceP0 = this.sommet_gauche();
        int indiceP1 = voisin_sommet(nbrSommets, indiceP0, 1);
        int indiceP2 = voisin_sommet(nbrSommets, indiceP0, -1);

        Vecteur P0 = this.getPoint(indiceP0);
        Vecteur P1 = this.getPoint(indiceP1);
        Vecteur P2 = this.getPoint(indiceP2);

        int j = this.indice_sommet_distance_max(P0, P1, P2, indiceP0, indiceP1, indiceP2);
        if (j == -1) {
            liste_triangles.add(new Triangle(P0, P1, P2));

            Polygone polygone1 = this.new_polygone(indiceP1, indiceP2);
            if (polygone1.getPoints().length == 3) {
                liste_triangles.add(new Triangle(polygone1.getPoint(0), polygone1.getPoint(1), polygone1.getPoint(2)));
            } else {
                polygone1.trianguler(liste_triangles);
            }
        } else {
            Polygone polygone1 = this.new_polygone(indiceP0, j);
            Polygone polygone2 = this.new_polygone(j, indiceP0);

            if (polygone1.getPoints().length == 3) {
                liste_triangles.add(new Triangle(polygone1.getPoint(0), polygone1.getPoint(1), polygone1.getPoint(2)));
            } else {
                polygone1.trianguler(liste_triangles);
            }

            if (polygone2.getPoints().length == 3) {
                liste_triangles.add(new Triangle(polygone2.getPoint(0), polygone2.getPoint(1), polygone2.getPoint(2)));
            } else {
                polygone2.trianguler(liste_triangles);
            }
        }
        return liste_triangles;
    }
}