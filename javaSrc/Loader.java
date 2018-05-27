import java.io.*;
import java.util.*;

public class Loader {
    
    /* Map of all imported characters */
    private Map<String, Character> characters;
    /* Map of Lists containing values for all attributes*/
    private Map<String, List<String>> cAttributes;
    
    public Loader(String gameFilename) throws IOException {
        characters = new HashMap<>();
        cAttributes = new HashMap<>();
        load(gameFilename);
    }
    
    /* Loads the attributes then the characters */
    public void load(String gameFilename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(gameFilename));
        loadAttributes(in);
        loadCharacters(in);
        in.close();
    }
    
    /* Attribute list must have no lines between attributes. The list must
     * be separated by at least a line from the first character */
    public void loadAttributes(BufferedReader in) throws IOException {
        
        String line;
        String [] tokens;
        List<String> attributes;
        
        /* Ensures attributes are read */
        boolean readAttributes = false;
        
        /* While lines are left in the file */
        while ((line = in.readLine()).length() > 0) {
            /* Splits a string by white space */
            tokens = line.trim().split("\\s+");
            /* Creates the ArrayList of values of an attribute */
            attributes = new ArrayList<>(Arrays.asList(tokens));
            /* Sets key and value in HashMap */
            cAttributes.put(tokens[0], attributes);
            readAttributes = true;
        }
        
        if (!readAttributes)
            throw new IOException("Error: Failed to read attributes list. List must have" +
                    " no blank lines between attributes or at the beginning of the file.");
    }
    
    /* Characters must be separated from each other by at least 1 line. Character
     * attributes must not be separated by lines. Only the first token will be
     * taken for the character name. */
    public void loadCharacters(BufferedReader in) throws IOException {
        
        String line;
        String [] tokens;
        String charName = null;
        HashMap<String, String> attributes = new HashMap<>();
        
        /* Read First Line */
        line = in.readLine();
        
        /* Continue until no more characters can be found. (Last character may
         * have trailing new lines) */
        while (true) {
            
            /* Skip blank lines */
            while (line.trim().length() == 0)
                /* If EOF is found, all characters have been loaded */
                if((line = in.readLine()) == null)
                    return;
            
            /* Splits a line by white space */
            tokens = line.trim().split("\\s+");
            
            /* If a single token set name, otherwise store the attribute*/
            if (tokens.length == 1)
                charName = tokens[0];
            else
                attributes.put(tokens[0], tokens[1]);
            
            /* Read next line, if EOF, add the player and return*/
            if((line = in.readLine()) == null) {
                characters.put(charName, new Character(charName, attributes));
                return;
            }
            
            /* If new line, add the player and continue reading*/
            if (line.trim().length() == 0) {
                characters.put(charName, new Character(charName, attributes));
                attributes = new HashMap<>();
            }
        }
        
    }
    
    public Map<String, Character> getCharacters() {
        return characters;
    }
    
    public Map<String, List<String>> getAllAttributes() {
        return cAttributes;
    }
}
