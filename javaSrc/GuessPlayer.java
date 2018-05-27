import java.io.*;
import java.util.*;

public class GuessPlayer {
   
    /* Map of all Characters. Character name is the key */
    protected Map<String, Character> characters;
    /* HashMap of all attributes containing a list of options */
    protected Map<String, List<String>> cAttributes;
    /* Chosen character loaded from file */
    protected Character chosenChar;
    
    public GuessPlayer(String gameFilename, String chosenName) throws IOException {
        // Load all characters
        Loader loader = new Loader(gameFilename);
        // Get all attributes to use
        cAttributes = loader.getAllAttributes();
        // Get all characters to use
        characters = loader.getCharacters();
        // Get the character that was chosen
        chosenChar = characters.get(chosenName);
    }
    
    public boolean answer(Guess currGuess) {
        //if the chosen character has the same value as the guess then return true
        if(currGuess.getType().equals(Guess.GuessType.Attribute)) {
            return chosenChar.getAttributes().get(currGuess.getAttribute()).equals(currGuess.getValue());
        }
        else {
            return true;
        }
    } // end of answer()
    
    public boolean receiveAnswer(Guess currGuess, boolean answer) {
        
        // Return true if there is only 1 character left
        if (characters.size() == 1)
            return true;
        // For each character
        for (Character character : new ArrayList<>(characters.values())) {

            if (answer) {
                // remove any character that doesn't have the attribute
                if (!character.getAttributes().get(currGuess.getAttribute()).equals(currGuess.getValue())) {
                    characters.remove(character.getName());
                }
            } else {
                // remove any character that has the attribute
                if (character.getAttributes().get(currGuess.getAttribute()).equals(currGuess.getValue())) {
                    characters.remove(character.getName());
                }
            }
        }
        return false;
    } // end of receiveAnswer()
}
