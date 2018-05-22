import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Character {


    private String name;
    private HashMap<String, String> attributes;

    public Character(String name, HashMap<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public HashMap<String,String> getAttributes(){
        return attributes;
    }

    public String getName() {
        return name;
    }



}
