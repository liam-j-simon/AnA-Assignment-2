import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Loader {
    
    private String gameFilename;
    private List<Character> characters;
    private HashMap<String, List<String>> allAttributes;
    private HashMap<String, String> playerAttributes;
    
    public Loader(String gameFilename) {
        this.gameFilename = gameFilename;
        characters = new ArrayList<>();
        allAttributes = new HashMap<>();
        playerAttributes = new HashMap<>();
        load();
    }
    
    public void load() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(gameFilename));
            loadAttributes(in);
            loadCharacters(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadAttributes(BufferedReader in) throws Exception {
        
        String line;
        String [] tokens;
        List<String> attributes = new ArrayList<>();
        
        /* While lines are left in the file */
        while ((line = in.readLine()).length() > 0) {
            /* Splits a string by white space*/
            tokens = line.trim().split("\\s+");
            
            /* Creates the ArrayList of allAttributes */
            for (int i = 1; i < tokens.length; i++)
                attributes.add(tokens[i]);
            
            /* Sets key and value in HashMap */
            allAttributes.put(tokens[0], attributes);
            
            attributes.clear();
        }
    }
    
    public void loadCharacters(BufferedReader in) throws Exception {
        
        String line;
        String [] tokens;
        String charName = null;
        
        /* While lines are left in the file */
        while ((line = in.readLine()) != null) {
            
            /* If line is not blank */
            if (line.trim().length() > 0) {
                
                /* Splits a string by white space*/
                tokens = line.trim().split("\\s+");
                
                /* If single token, set name */
                if (tokens.length == 1) {
                    charName = tokens[0];
                    /* Store the attribute in HashMap */
                } else {
                    playerAttributes.put(tokens[0], tokens[1]);
                }
                /* Create character at blank line */
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
