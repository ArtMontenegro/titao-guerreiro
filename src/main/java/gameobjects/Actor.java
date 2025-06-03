package gameobjects;

public class Actor {

    private String name;
    private String desc;
    private Place location; // The place where the Actor is currently located

    public Actor(String aName, String aDesc, Place aPlace) {
        name = aName;
        desc = aDesc;
        location = aPlace;
    }

    // Accessor methods
    public String getName() {
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

    public void setLocation(Place aPlace) {
        location = aPlace;
    }
    public Place getLocation() {
        return location;
    }
}