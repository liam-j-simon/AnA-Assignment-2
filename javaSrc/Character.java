import java.util.Map;

public class Character {
    
    /* Character name */
    private String name;
    /* Character's attributes. Key is the attribute name. */
    private Map<String, String> attributes;

    public Character(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public Map<String,String> getAttributes(){
        return attributes;
    }

    public String getName() {
        return name;
    }
    
}
