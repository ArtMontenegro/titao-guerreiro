package gameobjects;

import java.util.ResourceBundle;

import gameobjects.GameMap.PlaceName;

/*
 * 
 * @author Arthur Oliveira Montenegro
 */

public class Place {

    private String nameKey;
    private String descKey;
    private boolean canEnter;
    private PlaceName n, s, e, w;

    private static ResourceBundle currentLanguage;

    // Class constructor method
    public Place(String aNameKey, String aDescKey, boolean aCanEnter, PlaceName aN, PlaceName aS, PlaceName aE, PlaceName aW) {
        this.nameKey = aNameKey;
        this.descKey = aDescKey;
        this.canEnter = aCanEnter;
        this.n = aN;
        this.s = aS;
        this.e = aE;
        this.w = aW;
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

    public boolean getCanEnter() {
        return canEnter;
    }
    public void setCanEnter(boolean aCanEnter) {
        this.canEnter = aCanEnter;
    }

    public PlaceName getN() {
        return n;
    }
    public void setN(PlaceName aN) {
        this.n = aN;
    }

    public PlaceName getS() {
        return s;
    }
    public void setS(PlaceName aS) {
        this.s = aS;
    }

    public PlaceName getE() {
        return e;
    }
    public void setE(PlaceName aE) {
        this.e = aE;
    }

    public PlaceName getW() {
        return w;
    }
    public void setW(PlaceName aW) {
        this.w = aW;
    }
}