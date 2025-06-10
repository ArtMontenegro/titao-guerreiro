package gameobjects;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Actor {

    private final String nameKey;
    private final String descKey;
    private Place location; // The place where the Actor is currently located

    public Actor(String aNameKey, String aDescKey, Place aPlace) {
        this.nameKey = aNameKey;
        this.descKey = aDescKey;
        this.location = aPlace;
    }

    // Accessor methods
    public String getName(ResourceBundle bundle) {
        try {
            return bundle.getString(nameKey);
        } catch (MissingResourceException e) {
            return "[Missing key: " + nameKey + "]";
        }
    }

    public String getDesc(ResourceBundle bundle) {
        try {
            return bundle.getString(descKey);
        } catch (MissingResourceException e) {
            return "[Missing key: " + descKey + "]";
        }
    }

    public Place getLocation() {
        return location;
    }
    public void setLocation(Place aPlace) {
        this.location = aPlace;
    }
}