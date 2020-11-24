package unice.l3miage.cpoo.tp4;

import unice.l3miage.cpoo.tp4.Shape.*;
import unice.l3miage.cpoo.tp4.Shape.Rectangle;
import unice.l3miage.cpoo.tp4.ShapeBuilder.*;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */
public class Main {
    /*
     * Vars
     */
    private static String content;

    /*
     * Methods
     */

    // Help printer
    private static void printHelp() {
        System.out.println("Utilisation :\n --help, -h \n --input <path to svg file> \n Fait par CHAINTREUIL Benjamin et DELMARE Thomas.");
    }

    // M�thode g�rant l'ouverture et l'extraction du contenu du fichier .svg en un String
    private static String loadFile(String path) {
        try {
            File file = new File(path);
            if(file.isFile()) {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                StringWriter out = new StringWriter();
                int b;

                while ((b=in.read()) != -1) {
                    out.write(b);
                }

                out.flush();
                out.close();
                in.close();
                return out.toString();
            } else {
                System.out.println("Error: Enter a valid file path.");
                System.exit(1);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }

    // Main
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            switch(args[0]) {
                case "-h":
                    printHelp();
                    break;
                case "--help":
                    printHelp();
                    break;
                case "--input":
                    if(args.length == 2) {
                        content = loadFile(args[1]);

                        if(content == null || content.isBlank()) {
                            System.out.println("Error: File is empty.");
                            System.exit(1);
                        } else {
                            // Lecture du fichier
                            SVGParser input = new SVGParser(content);

                            // Affichage des balises
                            System.out.println("Nombre de balise : " + input.nbrTags());
                            System.out.println("----- Balises -----");

                            input.displayTags();

                            // Initialisation de la triangulation
                            Triangle[] triangulation;
                            SVGGenerator output = new SVGGenerator(content);

                            // Affichage des shapes et de leurs propriétés

                            /*
                            ** Polygones
                             */
                            System.out.println("----- Polygones -----");

                            PolygonBuilder polygoneBuilder = new PolygonBuilder(input.getTags());
                            Polygone[] polygones;
                            if ((polygones = polygoneBuilder.getShapes()) == null) {
                                System.out.println("Aucun polygone dans le fichier svg !");
                            } else {
                                for (int i = 0; i < polygones.length; i++) {
                                    // Description du polygone
                                    System.out.println("===> Polygone #" + (i + 1));

                                    System.out.println("Nombre de points : " + polygones[i].nbrPoints());
                                    System.out.println("Périmètre : " + polygones[i].perimètre());
                                    System.out.print("Barycentre : ");
                                    polygones[i].barycentre().print();

                                    System.out.println("\nListe de points :");
                                    for (int j = 0; j < polygones[i].nbrPoints(); j++) {
                                        polygones[i].getPoint(j).print();
                                    }

                                    // Triangulation
                                    System.out.println("\nTriangulation en cours...");
                                    triangulation = polygones[i].trianguler();
                                    System.out.println("Triangulation terminée !");
                                    System.out.println(triangulation.length + " triangles calculés.");

                                    System.out.println("\n----- Triangles du polygone #" + (i + 1) + " -----");
                                    for (Triangle t : triangulation) {
                                        t.OA.print();
                                        t.OB.print();
                                        t.OC.print();
                                        System.out.print("\n");
                                    }

                                    output.addTriangulation(triangulation);
                                }
                            }

                            /*
                            ** Circle
                             */
                            System.out.println("----- Circles -----");

                            CircleBuilder circleBuilder = new CircleBuilder(input.getTags());
                            Circle[] circles;
                            if ((circles = circleBuilder.getShapes()) == null) {
                                System.out.println("Aucun circle dans le fichier svg !");
                            } else {
                                for (int i = 0; i < circles.length; i++) {
                                    // Description du circle
                                    System.out.println("===> Circle #" + (i + 1));

                                    System.out.println("Centre : ");
                                    circles[i].getCenter().print();
                                    System.out.println("Rayon : " + circles[i].getRadius());
                                    System.out.println("Périmètre : " + circles[i].perimètre());
                                    // TODO: Add area

                                    // Triangulation
                                    System.out.println("\nTriangulation en cours...");
                                    triangulation = circles[i].toPolygone().trianguler();
                                    System.out.println("Triangulation terminée !");
                                    System.out.println(triangulation.length + " triangles calculés.");

                                    System.out.println("\n----- Triangles du cercle #" + (i + 1) + " -----");
                                    for (Triangle t : triangulation) {
                                        t.OA.print();
                                        t.OB.print();
                                        t.OC.print();
                                        System.out.print("\n");
                                    }

                                    output.addTriangulation(triangulation);
                                }
                            }

                            /*
                             ** Ellipses
                             */
                            System.out.println("----- Ellipses -----");

                            EllipseBuilder ellipseBuilder = new EllipseBuilder(input.getTags());
                            Ellipse[] ellipses;
                            if ((ellipses = ellipseBuilder.getShapes()) == null) {
                                System.out.println("Aucune ellipse dans le fichier svg !");
                            } else {
                                for (int i = 0; i < ellipses.length; i++) {
                                    // Description de l'ellipse
                                    System.out.println("===> Ellipse #" + (i + 1));

                                    System.out.println("Centre : ");
                                    ellipses[i].getCenter().print();
                                    System.out.println("Rx : " + ellipses[i].getRadiusX());
                                    System.out.println("Ry : " + ellipses[i].getRadiusY());
                                    System.out.println("Périmètre : " + ellipses[i].perimètre());
                                    // TODO: Add area

                                    // Triangulation
                                    System.out.println("\nTriangulation en cours...");
                                    triangulation = ellipses[i].toPolygone().trianguler();
                                    System.out.println("Triangulation terminée !");
                                    System.out.println(triangulation.length + " triangles calculés.");

                                    System.out.println("\n----- Triangles de l'ellipse #" + (i + 1) + " -----");
                                    for (Triangle t : triangulation) {
                                        t.OA.print();
                                        t.OB.print();
                                        t.OC.print();
                                        System.out.print("\n");
                                    }

                                    output.addTriangulation(triangulation);
                                }
                            }

                            /*
                             ** Rectangles
                             */
                            System.out.println("----- Rectangles -----");

                            RectBuilder rectangleBuilder = new RectBuilder(input.getTags());
                            Rectangle[] rectangles;
                            if ((rectangles = rectangleBuilder.getShapes()) == null) {
                                System.out.println("Aucun rectangle dans le fichier svg !");
                            } else {
                                for (int i = 0; i < rectangles.length; i++) {
                                    // Description du rectangle
                                    System.out.println("===> Rectangle #" + (i + 1));

                                    // TODO: add barycentre
                                    System.out.println("Périmètre : " + rectangles[i].perimètre());
                                    // TODO: Add area

                                    System.out.println("\nListe de points :");
                                    for (int j = 0; j < 4; j++) {
                                        rectangles[i].toPolygone().getPoint(j).print();
                                    }

                                    // Triangulation
                                    System.out.println("\nTriangulation en cours...");
                                    triangulation = rectangles[i].toPolygone().trianguler();
                                    System.out.println("Triangulation terminée !");
                                    System.out.println(triangulation.length + " triangles calculés.");

                                    System.out.println("\n----- Triangles du rectangle #" + (i + 1) + " -----");
                                    for (Triangle t : triangulation) {
                                        t.OA.print();
                                        t.OB.print();
                                        t.OC.print();
                                        System.out.print("\n");
                                    }

                                    output.addTriangulation(triangulation);
                                }
                            }

                            /*
                             ** Lines
                             */
                            System.out.println("----- Lines -----");

                            LineBuilder lineBuilder = new LineBuilder(input.getTags());
                            Line[] lines;
                            if ((lines = lineBuilder.getShapes()) == null) {
                                System.out.println("Aucune line dans le fichier svg !");
                            } else {
                                for (int i = 0; i < lines.length; i++) {
                                    // Description du rectangle
                                    System.out.println("===> Line #" + (i + 1));

                                    System.out.println("\nListe de points :");
                                    for (Vecteur p : lines[i].getPoints()) {
                                        p.print();
                                    }
                                }
                            }

                            /*
                             ** Polylines
                             */
                            System.out.println("----- Polylines -----");

                            PolylineBuilder polylineBuilder = new PolylineBuilder(input.getTags());
                            Polyline[] polylines;
                            if ((polylines = polylineBuilder.getShapes()) == null) {
                                System.out.println("Aucune polyline dans le fichier svg !");
                            } else {
                                for (int i = 0; i < polylines.length; i++) {
                                    // Description du rectangle
                                    System.out.println("===> Polyline #" + (i + 1));

                                    System.out.println("\nListe de points :");
                                    for (Vecteur p : polylines[i].getPoints()) {
                                        p.print();
                                    }
                                }
                            }

                            // Génération du SVG
                            System.out.println("----- Génération du SVG -----");
                            output.export();
                        }
                        break;
                    }
                    printHelp();
                    break;
                default:
                    printHelp();
                    break;
            }
        } else {
            printHelp();
        }
    }
}
