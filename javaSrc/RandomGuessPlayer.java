import java.io.*;
import java.util.*;


/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer extends GuessPlayer implements Player
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
    public RandomGuessPlayer(String gameFilename, String chosenName) throws IOException {
        super(gameFilename, chosenName);
    } // end of RandomGuessPlayer()
    
    // Random guessing: Chooses a random attribute and a random
    // attribute value to guess
    public Guess guess() {
        // Guess
        //check array size
        if(characters.size() >1) {
            boolean check = false;
            List<String> keysAsArray = new ArrayList<>(cAttributes.keySet());
            //new random
            Random rand = new Random();
            String key;
            List<String> value;
            String specificAttribute;
            do {
                int valueSize;
                //check that the attributes have
                do {
                    //get random key
                    key = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                    //get value list with key
                    value = cAttributes.get(key);
                    valueSize = value.size();
                } while (valueSize == 0);
                //get random attribute within value
                int attNum = rand.nextInt(valueSize);
                //get specific attribute
                specificAttribute = value.get(attNum);
                //check that attribute exists in players before guess
                for (Character character : new ArrayList<>(characters.values())) {
                    if (character.getAttributes().get(key).equals(specificAttribute)) {
                        check = true;
                    }
                }
            }while(!check);
            //remove when guessed #machine learning
            value.remove(specificAttribute);
            return new Guess(Guess.GuessType.Attribute, key, specificAttribute);
        }
        else{
            return new Guess(Guess.GuessType.Person,"", characters.get(
                    new ArrayList<>(characters.keySet()).get(0)).getName());
        }
    } // end of guess()

} // end of class RandomGuessPlayer