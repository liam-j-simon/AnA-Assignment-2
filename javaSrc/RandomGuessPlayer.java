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
    
    
    public Guess guess() {
        // Guess
        //check array size
        if(characters.size() >1) {
            List<String> keysAsArray = new ArrayList<>(pAttributes.keySet());
            //new random
            Random rand = new Random();
            String key;
            List<String> value;
            int valueSize;
            do{
                //get random key
                key = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                //get value list with key
                value = pAttributes.get(key);
                valueSize = value.size();
            }while(valueSize == 0);
            //get random attribute within value
            int attNum = rand.nextInt(valueSize);
            //get specific attribute
            String specificAttribute = value.get(attNum );
            value.remove(specificAttribute);

            return new Guess(Guess.GuessType.Attribute, key, specificAttribute);
        }
        else{
            return new Guess(Guess.GuessType.Person,"", characters.get(
                    new ArrayList<>(characters.keySet()).get(0)).getName());
        }
    } // end of guess()

} // end of class RandomGuessPlayer