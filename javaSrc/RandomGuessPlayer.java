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
    public RandomGuessPlayer(String gameFilename, String chosenName) throws IOException
    {

        Loader loader = new Loader(gameFilename);

        pAttributes = loader.getAllAttributes();
        
        charList = loader.getCharacters();
        for(Character character : charList){
            if(character.getName().equals(chosenName)) {
                chosenChar = character;
            }
        }



    } // end of RandomGuessPlayer()


    public Guess guess() {
        // Guess
        //check array size
        if(charList.size() >1) {
            List<String> keysAsArray = new ArrayList<>(pAttributes.keySet());
            //new random
            Random rand = new Random();
            String key;
            List<String> value;
            int valueSize;
            do{
                //get random key
                key = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                //get value array with key
                value = pAttributes.get(key);
                valueSize = value.size();
            }while(valueSize == 0);
            //get random attribute within value
            int attNum = rand.nextInt(valueSize);
            //get specific attibute
            String specificAttribute = value.get(attNum );
            value.remove(specificAttribute);

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

        if(charList.size() ==1){
            return true;
        }

        if(answer){
            //if target character has the attribute
            ListIterator<Character> character = charList.listIterator();
            //loop and remove any character that doesnt have the attribute
            while(character.hasNext()) {
                if(!character.next().getAttributes().get(currGuess.getAttribute()).equals(currGuess.getValue())){
                    character.remove();
                }
            }
            return false;

        }
        else{
            //if target doesnt have attribute
            ListIterator<Character> character = charList.listIterator();
            //loop and remove any character that has the attribute
            while(character.hasNext()) {
                if(character.next().getAttributes().get(currGuess.getAttribute()).equals(currGuess.getValue())){
                    character.remove();
                }

            }

            return false;

        }
        //return false;
    } // end of receiveAnswer()

} // end of class RandomGuessPlayer
