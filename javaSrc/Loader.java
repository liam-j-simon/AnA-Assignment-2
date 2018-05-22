import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Loader {
	
	private String gameFilename;
	private Collection<Character> characters;
	private HashMap<String, ArrayList<String>> attributes;
	
	public Loader(String gameFilename) {
		this.gameFilename = gameFilename;
	}
	
	public void loadAttributes() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(gameFilename));
		} catch (IOException e) {
		
		}
	}
	
	public void loadCharacters() {

	}
	
	public Collection<Character> getCharList() {
		return characters;
	}
	
	public HashMap<String, ArrayList<String>> getPlayerAttributes() {
		return attributes;
	}
}
