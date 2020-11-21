package tp3.v2;

import java.util.ArrayList;

/**
 * Classe Polygone
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */

public class Polygone {
    // Constructor and constructor variable
	private Vecteur[] points;
		
	public Polygone(Vecteur... points) throws RuntimeException{
		if (points.length < 3) {
			throw new RuntimeException("Nbr de points < 3");
		}
		
		int dim = points[0].dimension();
		for(Vecteur point: points) {
			if(point.dimension() != dim) {
				throw new RuntimeException("Vecteurs de dim différentes");
			}
		}
		
		this.points = new Vecteur[points.length];
		System.arraycopy(points, 0, this.points, 0, points.length);
	}

	// Getters and setters
	
	public Vecteur[] getPoints() {
		Vecteur[] foo = new Vecteur[this.points.length];
		System.arraycopy(this.points, 0, foo, 0, this.points.length);
		return foo;
	}

	public Vecteur getPoint(int i) throws RuntimeException{
		if (i > this.points.length) {
			throw new RuntimeException("i > nbr de points");
		} else {
			return this.points[i];
		}
	}

	public Polygone setPoint(Vecteur... points) {
		Vecteur[] new_points = new Vecteur[points.length + this.points.length];
		System.arraycopy(this.points, 0, new_points, 0, this.points.length);
		System.arraycopy(points, 0, new_points, this.points.length, points.length);
		return new Polygone(new_points);
	}
	
	/*
	 * Methods
	 */
	
	// Retourne le nombre de points constituant le polygone
	public int nbrPoints() {
		return this.points.length;
	}
	
	// Détermine et renvoi le barycentre du polygone
	public Vecteur barycentre() {
		Vecteur foo = Vecteur.add(this.points);
		return foo.multK(1.0/this.points.length);
	}
	
	// Calcul et renvoi le périmètre du polygone
	public double perimetre() {
		double perimetre = 0;
		
		for(int i = 0; i < this.points.length; i++) {
			if (i < this.points.length - 1) {
				perimetre += Vecteur.add(this.points[i], this.points[i+1].opposé()).length();
			} else {
				perimetre += Vecteur.add(this.points[i], this.points[0].opposé()).length();
			}
		}
		return perimetre;
	}
	
	/*
	 * Triangulation
	 */
	
	// Renvoi l'indice du sommet le plus à gauche du polygone.
	private int sommet_gauche() {
		double x = this.points[0].get(0);
		int k = 0;
		for(int i = 1; i < this.points.length; i++) {
			if(this.points[i].get(0) < x) {
				x = this.points[i].get(0);
				k = i;
			}
		}
		return k;
	}

	// Retourne l'indice du sommet voisin à celui de l'indice en fonction du déplacement
	private static int voisin_sommet(int nbrSommets, int indice, int dep) {
		int indiceVoisin = (indice + dep) % nbrSommets;
		if (indiceVoisin == -1) {
			indiceVoisin += nbrSommets;
		}
		return indiceVoisin;
	}

	// Renvoi la norme de la composante Z du produit vectoriel de POP1 et POPM
	private static double produit_vect_Z (Vecteur P0, Vecteur P1, Vecteur M) {
		return (P1.get(0) - P0.get(0)) * (M.get(1) - P0.get(1)) - (P1.get(1) - P0.get(1)) * (M.get(0) - P0.get(0));
	}

	// Renvoi un booléen traduisant de la présence du point M dans le triangle délimité par P0, P1, P2
	private static boolean point_dans_triangle(Vecteur P0, Vecteur P1, Vecteur P2, Vecteur M) {
		return produit_vect_Z(P0, P1, M) > 0 && produit_vect_Z(P1, P2, M) > 0 && produit_vect_Z(P2, P0, M) > 0;
	}

	// Renvoi l'indice du sommet du polygone appartenant au triangle P0, P1, P2 et qui est à la plus grande distance du côté P1P2
	private int indice_sommet_distance_max(Vecteur P0, Vecteur P1, Vecteur P2, int indice_P0, int indice_P1, int indice_P2) {
		int n = this.points.length;
		double distance = 0.0;
		int k = -1;

		for(int i = 0; i < n; i++) {
			if (i != indice_P0 && i != indice_P1 && i != indice_P2) {
				Vecteur M = this.points[i];
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

	// Renvoi un nouveau polygone constitué des points compris entre l'indice iStart et iEnd
	private Polygone new_polygone(int iStart, int iEnd) {
		int n = this.points.length;
		ArrayList<Vecteur> sommets = new ArrayList<Vecteur>();
		int i = iStart;
		while(i != iEnd) {
			sommets.add(this.points[i]);
			i = voisin_sommet(n, i, 1);
		}
		sommets.add(this.points[iEnd]);

		Vecteur[] s = new Vecteur[sommets.size()];
		s = sommets.toArray(s);
		return new Polygone(s);
	}
	
	// Méthodes pour trianguler un polygone
	public Triangle[] trianguler() {
		ArrayList<Triangle> liste_triangles = new ArrayList<Triangle>();
		liste_triangles = this.trianguler(liste_triangles);
		
		Triangle[] triangles = new Triangle[liste_triangles.size()];
		triangles = liste_triangles.toArray(triangles);
		return triangles;
	}

	private ArrayList<Triangle> trianguler(ArrayList<Triangle> liste_triangles) {
		int nbrSommets = this.points.length;

		int indiceP0 = this.sommet_gauche();
		int indiceP1 = voisin_sommet(nbrSommets, indiceP0, 1);
		int indiceP2 = voisin_sommet(nbrSommets, indiceP0, -1);
		
		Vecteur P0 = this.points[indiceP0];
		Vecteur P1 = this.points[indiceP1];
		Vecteur P2 = this.points[indiceP2];

		int j = this.indice_sommet_distance_max(P0, P1, P2, indiceP0, indiceP1, indiceP2);
		if (j == -1) {
			liste_triangles.add(new Triangle(P0, P1, P2));

			Polygone polygone1 = this.new_polygone(indiceP1, indiceP2);
			if (polygone1.points.length == 3) {
				liste_triangles.add(new Triangle(polygone1.getPoint(0), polygone1.getPoint(1), polygone1.getPoint(2)));
			} else {
				polygone1.trianguler(liste_triangles);
			}
		} else {
			Polygone polygone1 = this.new_polygone(indiceP0, j);
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
