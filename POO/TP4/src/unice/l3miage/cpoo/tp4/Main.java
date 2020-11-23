package unice.l3miage.cpoo.tp4;

import unice.l3miage.cpoo.tp4.Shape.*;
import unice.l3miage.cpoo.tp4.ShapeBuilder.*;

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

                            // Affichage des polygones et leurs propri�t�s
                            System.out.println("----- Polygones -----");


                            // Test pour Polygone
                            // PolygonBuilder pBuilder = new PolygonBuilder(input.getTags());
                            // Polygone[] p = pBuilder.getShapes();

                            // Test pour Circle
                            CircleBuilder cBuilder = new CircleBuilder(input.getTags());
                            Circle[] p = cBuilder.getShapes();

                            // Test pour Ellipse
                            // EllipseBuilder eBuilder = new EllipseBuilder(input.getTags());
                            // Ellipse[] e = eBuilder.getShapes();

                            // Test pour Line
                            // LineBuilder lBuilder = new LineBuilder(input.getTags());
                            // Line[] l = lBuilder.getShapes();

                            // Test pour Polyline
                            // PolylineBuilder plBuilder = new PolylineBuilder(input.getTags());
                            // Polyline[] pl = plBuilder.getShapes();

                            // Test pour Rect
                            // RectBuilder rBuilder = new RectBuilder(input.getTags());
                            // Rectangle[] r = rBuilder.getShapes();


                            if (p != null) {
                                Triangle[][] triangulations = new Triangle[p.length][];

                                // On traite chaque polygone un par un
                                for (int i = 0; i < p.length; i++) {
                                    System.out.println("===> Polygone #" + (i + 1));

                                    System.out.println("Nombre de points : " + p[i].nbrPoints());
                                    System.out.println("P�rim�tre : " + p[i].perimètre());
                                    System.out.print("Barycentre : ");
                                    p[i].barycentre().print();

                                    System.out.println("\nListe de points :");
                                    for (int j = 0; j < p[i].nbrPoints(); j++) {
                                        p[i].getPoint(j).print();
                                    }

                                    // Triangulation
                                    System.out.println("\nTriangulation en cours...");
                                    triangulations[i] = p[i].trianguler();
                                    System.out.println("Triangulation termin�e !");
                                    System.out.println(triangulations[i].length + " triangles calcul�s.");

                                    System.out.println("\n----- Triangles du polygone #" + (i + 1) + " -----");
                                    for (Triangle t : triangulations[i]) {
                                        t.OA.print();
                                        t.OB.print();
                                        t.OC.print();
                                        System.out.print("\n");
                                    }
                                }

                            // G�n�ration du SVG
                            System.out.println("----- G�n�ration du SVG -----");
                            SVGGenerator output = new SVGGenerator(content);

                            for(Triangle[] triangulation: triangulations) {
                                output.addTriangulation(triangulation);
                            }

                            output.export();
                            }
                            System.out.println("\n----- All done ! =D -----");
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
