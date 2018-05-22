import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CharCreator {
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

    public CharCreator(String name, HashMap<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;

        sortMap();

    }

    public void sortMap() {
        Iterator it = attributes.entrySet().iterator();
        while (((Iterator) it).hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            switch (pair.getKey().toString()) {
                case "hairLength":
                    hairLength = pair.getValue().toString();
                    break;
                case "glasses":
                    glasses = pair.getValue().toString();
                    break;
                case "facialHair":
                    facialHair = pair.getValue().toString();
                    break;
                case "eyeColour":
                    eyeColour = pair.getValue().toString();
                    break;
                case "pimples":
                    pimples = pair.getValue().toString();
                    break;
                case "hat":
                    hat = pair.getValue().toString();
                    break;
                case "hairColour":
                    hairColor = pair.getValue().toString();
                    break;
                case "noseShape":
                    noseShape = pair.getValue().toString();
                    break;
                case "faceShape":
                    faceShape = pair.getValue().toString();
                    break;

            }
        }

    }
    public String getHairLength() {
        return hairLength;
    }

    public String getFacialHair() {
        return facialHair;
    }

    public String getGlasses() {
        return glasses;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public String getPimples() {
        return pimples;
    }

    public String getHat() {
        return hat;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getNoseShape() {
        return noseShape;
    }

    public String getFaceShape() {
        return faceShape;
    }

    public String getName() {
        return name;
    }
}
