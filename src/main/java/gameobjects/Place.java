package gameobjects;

/*
 * 
 * @author Arthur Oliveira Montenegro
 */

public class Place {

    private String name, desc;
    private int n, s, e, w;

    // Class constructor method
    public Place(
        String aName, String aDesc, int aN, int aS, int aE, int aW) {
        name = aName;
        desc = aDesc;
        n = aN;
        s = aS;
        e = aE;
        w = aW;
    }
    
    // Accessor methods

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String aDesc) {
        desc = aDesc;
    }

    public int getN() {
        return n;
    }
    public void setN(int aN) {
        n = aN;
    }

    public int getS() {
        return s;
    }
    public void setS(int aS) {
        s = aS;
    }

    public int getE() {
        return e;
    }
    public void setE(int aE) {
        e = aE;
    }

    public int getW() {
        return w;
    }
    public void setW(int aW) {
        w = aW;
    }
}