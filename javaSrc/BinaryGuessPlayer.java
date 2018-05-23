import java.io.*;
import java.util.*;

/**
 * Binary-search based guessing player.
 * This player is for task C.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class BinaryGuessPlayer extends GuessPlayer implements Player
{
    
    /**
     * Loads the game configuration from gameFilename, and also store the chosen
     * person.
     *
     * @param gameFilename Filename of game configuration.
     * @param chosenName Name of the chosen person for this player.
     * @throws IOException If there are IO issues with loading of gameFilename.
     *    Note you can handle IOException within the constructor and remove
     *    the "throws IOException" method specification, but make sure your
     *    implementation exits gracefully if an IOException is thrown.
     */
    public BinaryGuessPlayer(String gameFilename, String chosenName) throws IOException {
        super(gameFilename, chosenName);
    } // end of BinaryGuessPlayer()


    public Guess guess() {

        int match = 0, notMatch = 0;
        int minDifference = Math.abs(characters.size() / 2) + 1;
        
        String attributeGuess = null;
        String valueGuess = null;
        
        boolean minFound = false;
        
        if(characters.size() > 1) {
            /* For each attribute */
            earlyTerminate:
            for (String attribute : pAttributes.keySet()) {
                /* For each attribute value */
                for (String value : pAttributes.get(attribute)) {
                    /* For each character */
                    for (Character character : characters.values()) {
                        /* If the characters attribute matches the value */
                        if (character.getAttributes().get(attribute).equals(value)) {
                            match++;
                        } else {
                            notMatch++;
                        }
                    }
                    
                    /* Compare */
                    int difference =
                            Math.abs((characters.size() / 2) - match) >
                            Math.abs((characters.size() / 2) - notMatch) ?
                            Math.abs((characters.size() / 2) - match) :
                            Math.abs((characters.size() / 2) - notMatch);
                    
                    if (difference < minDifference) {
                        attributeGuess = attribute;
                        valueGuess = value;
                        minDifference = difference;
                    }
                    System.out.println("Difference: " + difference);
                    System.out.println("MinDifference: " + minDifference + "\n");
                    
                    /* Stop searching if minimum difference is found.*/
                    if (difference == 0)
                        break earlyTerminate;
                    
                    match = 0;
                    notMatch = 0;
                }
            }
            
            pAttributes.get(attributeGuess).remove(valueGuess);
            return new Guess(Guess.GuessType.Attribute, attributeGuess, valueGuess);

        } else {
            return new Guess(Guess.GuessType.Person,"", characters.get(
                    new ArrayList<>(characters.keySet()).get(0)).getName());
        }
    } // end of guess()

} // end of class BinaryGuessPlayer
