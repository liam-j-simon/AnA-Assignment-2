import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Character {
    private String hairLength;
    private String glasses;
    private String facialHair;
    private String eyeColour;
    private String pimples;
    private String hat;
    private String hairColor;
    private String noseShape;
    private String faceShape;

    private String name;
    private HashMap<String, String> attributes;

    public Character(String name, HashMap<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;

        //sortMap();

    }

    public HashMap<String,String> getAttributes(){
        return attributes;
    }

    public String getName() {
        return name;
    }



}
