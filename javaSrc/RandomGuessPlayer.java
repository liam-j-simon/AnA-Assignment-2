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
    private List<Character> charList;
    private HashMap<String,List<String>> pAttributes;
    private Character chosenChar;

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

        Loader loader = new Loader(gameFilename);

        pAttributes = loader.getAllAttributes();

        charList = loader.getCharacters();
        for(Character character : charList){
            if(character.getName().equals(chosenName)) {
                chosenChar = character;
                charList.remove(character);
            }
        }


    } // end of RandomGuessPlayer()


    public Guess guess() {
        // Guess
        //get an array
        if(charList.size() >1) {
            List<String> keysAsArray = new ArrayList<>(pAttributes.keySet());
            //new random
            Random rand = new Random();
            //ArrayList<String> attributeList = pAttributes.get(keysAsArray.get(rand.nextInt(keysAsArray.size())));
            // placeholder, replace
            //get random key
            String key = keysAsArray.get(rand.nextInt(keysAsArray.size()));
            //get value with key
            List<String> value = pAttributes.get(key);
            //get random attribute within value
            int attNum = rand.nextInt(value.size());
            String specificAttribute = value.get(attNum);
            // remove from attribute pool
            value.remove(attNum);
            return new Guess(Guess.GuessType.Attribute, key, specificAttribute);
        }
        else{
            return new Guess(Guess.GuessType.Person,"",charList.get(0).getName());
        }
    } // end of guess()



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


        if(answer && chosenChar.getName().equals(currGuess.getValue())){
            return true;
        }
        for (Character character : charList) {
            //if its true
            if(answer){
                //remove all that dont have that attribute
                if(!chosenChar.getAttributes().get(currGuess.getType()).equals(currGuess.getValue())){
                    charList.remove(character);
                    return false;
                }
            }
            else{
                //else all that have the attribute
                if(chosenChar.getAttributes().get(currGuess.getType()).equals(currGuess.getValue())) {
                    charList.remove(character);
                    return false;
                }
            }
        }

        return false;
    } // end of receiveAnswer()

} // end of class RandomGuessPlayer
