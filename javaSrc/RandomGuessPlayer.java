import java.io.*;
import java.util.*;

/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer implements Player
{
    private ArrayList<Character> charList;
    private HashMap<String,ArrayList<String>> pAttributes;
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
    public RandomGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {
        //charList = loader.getCharList();
        //pAttributes = loader.getPlayerAttributes
    } // end of RandomGuessPlayer()


    public Guess guess() {
        // Guess
        //get an array
        List<String> keysAsArray = new ArrayList<String>(pAttributes.keySet());
        //new random
        Random rand = new Random();
        ArrayList<String> attributeList = pAttributes.get(keysAsArray.get(rand.nextInt(keysAsArray.size())));
        String specificAttribute = attributeList.get(rand.nextInt(attributeList.size()));
        // placeholder, replace
        //get random key
        String key = keysAsArray.get(rand.nextInt(keysAsArray.size()));
        //get value with key
        ArrayList<String> temp = pAttributes.get(key);
        //get random attribute within value
        int attNum = rand.nextInt(temp.size());
        //String specificAttribute = temp.get(attNum);
        // remove from attribute pool
        temp.remove(attNum);
        return new Guess(Guess.GuessType.Attribute, key, specificAttribute);
    } // end of guess()



    public boolean answer(Guess currGuess) {

        // placeholder, replace
        return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {

        // placeholder, replace
        return true;
    } // end of receiveAnswer()

} // end of class RandomGuessPlayer
