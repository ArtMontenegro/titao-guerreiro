package gameobjects;

import java.util.ResourceBundle;

public class Actor {

    private static ResourceBundle currentLanguage;
    private String nameKey;
    private String descKey;
    private Place location; // The place where the Actor is currently located

    public Actor(String aNameKey, String aDescKey, Place aPlace) {
        this.nameKey = aNameKey;
        this.descKey = aDescKey;
        this.location = aPlace;
    }

    // Accessor methods
    public static void setLanguage(ResourceBundle language) {
        currentLanguage = language;
    }

    public String getName() {
        return currentLanguage.getString(nameKey);
    }

    public String getDesc() {
        return currentLanguage.getString(descKey);
    }

    public Place getLocation() {
        return location;
    }
    public void setLocation(Place aPlace) {
        this.location = aPlace;
    }
}