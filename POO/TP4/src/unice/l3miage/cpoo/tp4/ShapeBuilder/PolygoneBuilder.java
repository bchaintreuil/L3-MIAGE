package unice.l3miage.cpoo.tp4.ShapeBuilder;
import unice.l3miage.cpoo.tp4.Shape.Polygone;
import unice.l3miage.cpoo.tp4.Vecteur;

import java.util.ArrayList;

public class PolygoneBuilder extends ShapeBuilder implements iTrianguler {
    String[] tags;
    Polygone[] polygones;

    public PolygoneBuilder(String[] tags) {
        this.tags = new String[tags.length];
        System.arraycopy(tags, 0, this.tags, 0, tags.length);
        polygones = this.generatePolygones();
    }

    // Génération des polygones extraits du fichier SVG
    private Polygone[] generatePolygones() {
        ArrayList<Polygone> p = new ArrayList<Polygone>();
        int coordsStart;
        int coordsEnd;
        String coordsSubStr;
        ArrayList<Vecteur> points;

        // Récupération des points
        for(int i = 0; i < this.tags.length; i++) {
            if(tags[i].contains("polygon")) {
                coordsStart = tags[i].indexOf("points=\"");
                coordsEnd = tags[i].indexOf("\"", coordsStart + 9);
                coordsStart += 8;

                coordsSubStr = tags[i].substring(coordsStart, coordsEnd);

                // On split les coordonées par rapport au espace/tab/etc...
                String[] pointsStr = coordsSubStr.split("(\\s+)");

                points = new ArrayList<Vecteur>();
                for(String point: pointsStr) {
                    points.add(new Vecteur(Double.parseDouble(point.split(",")[0]), Double.parseDouble(point.split(",")[1])));
                }
                p.add(new Polygone(points.toArray(new Vecteur[0])));
            }

        }
        return p.toArray(new Polygone[0]);
    }

    // Renvoi un tableau contenant l'ensemble des polygones définis dans le fichier SVG
    public Polygone[] getPolygones() {
        Polygone[] p = new Polygone[this.polygones.length];
        System.arraycopy(this.polygones, 0, p, 0, this.polygones.length);
        return p;
    }
}
