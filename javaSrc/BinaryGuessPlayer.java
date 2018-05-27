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

    /* Binary search guessing: Finds the attribute that is closest to removing
     * half of the remaining characters */
    public Guess guess() {
        /* Number of attribute matches for remaining characters */
        int match = 0, nonMatch = 0;
        /* The minimum difference between 'half the number of characters remaining'
         * and the closest 'match/non-match'. Initially set as half the number of
         * characters remaining (largest possible difference) */
        int minDifference = characters.size() / 2;
        /* Stores the guess with the minDifference */
        String attributeGuess = null;
        String valueGuess = null;
        
        /* If there is more than 1 character remaining */
        if(characters.size() > 1) {
            /* For each attribute */
            earlyTerminate:
            for (String attribute : cAttributes.keySet()) {
                /* For each attribute value remaining */
                for (String value : cAttributes.get(attribute)) {
                    /* For each character remaining */
                    for (Character character : characters.values()) {
                        /* If the characters attribute matches the value */
                        if (character.getAttributes().get(attribute).equals(value)) {
                            match++;
                        } else {
                            nonMatch++;
                        }
                    }
                    
                    /* Compares the matches and non-matches and chooses
                     * the lowest difference */
                    int difference =
                            Math.abs((characters.size() / 2) - match) <
                            Math.abs((characters.size() / 2) - nonMatch) ?
                            Math.abs((characters.size() / 2) - match) :
                            Math.abs((characters.size() / 2) - nonMatch);
                    
                    /* Compares the current difference with the minimum
                     * difference so far and sets the relevant attribute value */
                    if (difference < minDifference) {
                        attributeGuess = attribute;
                        valueGuess = value;
                        minDifference = difference;
                    }
                    
                    /* Reset counters between attribute values */
                    match = 0;
                    nonMatch = 0;
    
                    /* Early termination if minimum possible
                     * difference is found.*/
                    if (difference == 0)
                        break earlyTerminate;
                }
            }
            
            /* Remove the value guessed and make guess */
            cAttributes.get(attributeGuess).remove(valueGuess);
            return new Guess(Guess.GuessType.Attribute, attributeGuess, valueGuess);
        
        /* If there is 1 character remaining, guess the character */
        } else {
            return new Guess(Guess.GuessType.Person,"", characters.get(
                    new ArrayList<>(characters.keySet()).get(0)).getName());
        }
    } // end of guess()

} // end of class BinaryGuessPlayer
