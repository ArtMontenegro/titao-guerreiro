package gameobjects;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Item {

    private final String nameKey;
    private final String descKey;
    private int value;

    public Item(String aNameKey, String aDescKey, int aValue) {
        this.nameKey = aNameKey;
        this.descKey = aDescKey;
        this.value = aValue;
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

    public int getValue() {
        return value;
    }
    public void setValue(int aValue) {
        this.value = aValue;
    }
}
