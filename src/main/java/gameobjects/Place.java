package gameobjects;

import gameobjects.Map.PlaceName;

/*
 * 
 * @author Arthur Oliveira Montenegro
 */

public class Place {

    private String name;
    private String desc;
    private boolean canEnter;
    private PlaceName n, s, e, w;

    // Class constructor method
    public Place(String aName, String aDesc, boolean aCanEnter, PlaceName aN, PlaceName aS, PlaceName aE, PlaceName aW) {
        name = aName;
        desc = aDesc;
        canEnter = aCanEnter;
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