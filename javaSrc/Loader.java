import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader {
    
    /* Map of all imported characters */
    private Map<String, Character> characters;
    /* Map of Lists containing values for all attributes*/
    private Map<String, List<String>> pAttributes;
    
    public Loader(String gameFilename) throws IOException {
        characters = new HashMap<>();
        pAttributes = new HashMap<>();
        load(gameFilename);
    }
    
    /* Loads the attributes then the characters */
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
            /* Splits a string by white space */
            tokens = line.trim().split("\\s+");
            /* Creates the ArrayList of values of an attribute */
            for (int i = 1; i < tokens.length; i++)
                attributes.add(tokens[i]);
            /* Sets key and value in HashMap */
            pAttributes.put(tokens[0], attributes);
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
            /* Create Character */
            } else {
                /* Add final attribute to last player */
                if (nextLine == null) {
                    tokens = line.trim().split("\\s+");
                    playerAttributes.put(tokens[0], tokens[1]);
                }
                /* Add new Character to Map */
                characters.put(charName, new Character(charName, playerAttributes));
                playerAttributes = new HashMap<>();
            }
        }
    }
    
    public Map<String, Character> getCharacters() {
        return characters;
    }
    
    public Map<String, List<String>> getAllAttributes() {
        return pAttributes;
    }
}
