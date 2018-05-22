import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Loader {
    
    private List<Character> characters;
    private HashMap<String, List<String>> allAttributes;
    
    public Loader(String gameFilename) throws IOException {
        characters = new ArrayList<>();
        allAttributes = new HashMap<>();
        load(gameFilename);
    }
    
    public void load(String gameFilename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(gameFilename));
        loadAttributes(in);
        loadCharacters(in);
        in.close();
    }
    
    public void loadAttributes(BufferedReader in) throws IOException {
        
        String line;
        String [] tokens;
        List<String> attributes;
        
        /* While lines are left in the file */
        while ((line = in.readLine()).length() > 0) {
            attributes = new ArrayList<>();
            /* Splits a string by white space*/
            tokens = line.trim().split("\\s+");
            /* Creates the ArrayList of values of an attribute */
            for (int i = 1; i < tokens.length; i++)
                attributes.add(tokens[i]);
            /* Sets key and value in HashMap */
            allAttributes.put(tokens[0], attributes);
        }
    }
    
    public void loadCharacters(BufferedReader in) throws IOException {
        
        String [] tokens;
        String charName = null;
        HashMap<String, String> playerAttributes = new HashMap<>();
        
        /* While lines are left in the file, keep track of current line and
         * nextLine to add the final player when EOF is reached */
        for (String nextLine, line = in.readLine(); line != null; line = nextLine) {
            nextLine = in.readLine();
            /* If line is not blank and have not reached end of file */
            if (line.trim().length() > 0 && nextLine != null) {
                /* Splits a string by white space*/
                tokens = line.trim().split("\\s+");
                /* If single token, set name */
                if (tokens.length == 1) {
                    charName = tokens[0];
                    /* Store the attribute in HashMap */
                } else {
                    playerAttributes.put(tokens[0], tokens[1]);
                }
            /* Create character */
            } else {
                characters.add(new Character(charName, playerAttributes));
                playerAttributes = new HashMap<>();
            }
        }
    }
    
    public List<Character> getCharacters() {
        return characters;
    }
    
    public HashMap<String, List<String>> getAllAttributes() {
        return allAttributes;
    }
}
