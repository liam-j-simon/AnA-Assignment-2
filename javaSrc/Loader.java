import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Loader {
	
	String gameFinename;
	Collection<Character> characters;
	HashMap<String, ArrayList<String>> attributes;
	
	public Loader(String gameFilename) {
		this.gameFinename = gameFilename;
	}
	
	public void loadAttributes() {
	
	}
	
	public void loadCharacters() {

	}
	
	public Collection<Character> getCharacters() {
		return characters;
	}
	
	public HashMap<String, ArrayList<String>> getAttributes() {
		return attributes;
	}
}
