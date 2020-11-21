package unice.l3miage.cpoo.tp4;

import java.util.ArrayList;

public interface iTrianguler {
    static ArrayList<Triangle> trianguler(ArrayList<Triangle> liste_triangles) {
        int nbrSommets = this.points.length;
        int indiceP0 = this.sommet_gauche();
        int indiceP1 = voisin_sommet(nbrSommets, indiceP0, 1);
        int indiceP2 = voisin_sommet(nbrSommets, indiceP0, -1);
        Vecteur P0 = this.points[indiceP0];
        Vecteur P1 = this.points[indiceP1];
        Vecteur P2 = this.points[indiceP2];
        int j = this.indice_sommet_distance_max(P0, P1, P2, indiceP0, indiceP1, indiceP2);
        Polygone polygone1;
        if (j == -1) {
            liste_triangles.add(new Triangle(P0, P1, P2));
            polygone1 = this.new_polygone(indiceP1, indiceP2);
            if (polygone1.points.length == 3) {
                liste_triangles.add(new Triangle(polygone1.getPoint(0), polygone1.getPoint(1), polygone1.getPoint(2)));
            } else {
                polygone1.trianguler(liste_triangles);
            }
        } else {
            polygone1 = this.new_polygone(indiceP0, j);
            Polygone polygone2 = this.new_polygone(j, indiceP0);
            if (polygone1.points.length == 3) {
                liste_triangles.add(new Triangle(polygone1.getPoint(0), polygone1.getPoint(1), polygone1.getPoint(2)));
            } else {
                polygone1.trianguler(liste_triangles);
            }

            if (polygone2.points.length == 3) {
                liste_triangles.add(new Triangle(polygone2.getPoint(0), polygone2.getPoint(1), polygone2.getPoint(2)));
            } else {
                polygone2.trianguler(liste_triangles);
            }
        }

        return liste_triangles;
    }
}
