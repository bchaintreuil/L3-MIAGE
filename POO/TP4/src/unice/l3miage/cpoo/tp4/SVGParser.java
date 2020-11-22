package unice.l3miage.cpoo.tp4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 * @author Benjamin CHAINTREUIL
 * @author Thomas DELMARE
 */
public class SVGParser {
    /*
     * Vars
     */
    private final String tagRegex = "<[^>]+>"; // Regex matchant les balises d'un fichier SVG
    private String content;
    private String[] tags;

    /*
     * Methods
     */

    // Récupération des balises
    private String[] getTags(String str, String regex) {
        ArrayList<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile(regex).matcher(str);

        while(m.find()) {
            // Removing comments and header.
            if(m.group(0).contains("<?") || m.group(0).contains("<!--")) {
                continue;
            }

            matches.add(m.group(0));
        }

        return matches.toArray(new String[0]);
    }

    // Parsing function
    private void parse() {
        this.tags = this.getTags(this.content, this.tagRegex);
    }

    // Renvoi le nombre de balise contenu dans le fichier SVG
    public int nbrTags() {
        return this.tags.length;
    }

    // Affiche à l'écran l'ensemble des balises contenus dans le fichier SVG
    public void displayTags() {
        for(int i = 0; i < this.nbrTags(); i++) {
            System.out.println("[Balise n°" + (i + 1) + "]");
            System.out.println(this.tags[i] + "\n");
        }
    }

    // Constructor
    public SVGParser(String content) {
        this.content = new String(content);
        this.parse();
    }

}
