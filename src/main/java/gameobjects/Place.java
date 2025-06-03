package gameobjects;

import gameobjects.Map.PlaceName;

/*
 * 
 * @author Arthur Oliveira Montenegro
 */

public class Place {

    private String name;
    private String desc;
    private PlaceName n, s, e, w;

    // Class constructor method
    public Place(String aName, String aDesc, PlaceName aN, PlaceName aS, PlaceName aE, PlaceName aW) {
        name = aName;
        desc = aDesc;
        n = aN;
        s = aS;
        e = aE;
        w = aW;
    }
    
    // Accessor methods
    public String getName(){
        return name;
    }
    public void setName(String aName) {
        this.name = aName;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String aDesc) {
        this.desc = aDesc;
    }

    public PlaceName getN() {
        return n;
    }
    public void setN(PlaceName aN) {
        n = aN;
    }

    public PlaceName getS() {
        return s;
    }
    public void setS(PlaceName aS) {
        s = aS;
    }

    public PlaceName getE() {
        return e;
    }
    public void setE(PlaceName aE) {
        e = aE;
    }

    public PlaceName getW() {
        return w;
    }
    public void setW(PlaceName aW) {
        w = aW;
    }
}