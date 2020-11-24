package unice.l3miage.cpoo.tp4;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */
public class SVGParser {
    /*
     * Vars
     */
    private final String tagRegex = "<[^>]+>"; // Regex matchant les balises d'un fichier SVG
    private final String content;
    private String[] tags;

    // Getters and setters
    public String[] getTags() {
        String[] foo = new String[this.tags.length];
        System.arraycopy(this.tags, 0, foo, 0, this.tags.length);
        return foo;
    }

    /*
     * Methods
     */

    // Constructor
    public SVGParser(String content) {
        this.content = content;
        this.parse();
    }

    // Parsing function
    private void parse() {
        this.tags = this.extractTags();
    }

    // Renvoi le nombre de balise contenu dans le fichier SVG
    public int nbrTags() {
        return this.tags.length;
    }

    // Affiche à l'écran l'ensemble des balises contenus dans le fichier SVG
    public void displayTags() {
        for (int i = 0; i < this.nbrTags(); i++) {
            System.out.println("[Balise n°" + (i + 1) + "]");
            System.out.println(this.tags[i] + "\n");
        }
    }

    // Récupération des balises
    private String[] extractTags() {
        ArrayList<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile(this.tagRegex).matcher(this.content);

        while (m.find()) {
            // Removing comments and header.
            if (m.group(0).contains("<?") || m.group(0).contains("<!--")) {
                continue;
            }
            matches.add(m.group(0));
        }
        return matches.toArray(new String[0]);
    }
}
