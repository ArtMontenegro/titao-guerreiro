package gameobjects;

public class Map {

    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST;
        public static final PlaceName NOEXIT = PlaceName.NOEXIT;
    }

    public enum PlaceName {
        LEADERHOUSE, YOURHOUSE, WORKSHOP, MONASTERY, ELDERHOUSE, MOUNTBASE, MORIAH,
        FOREST, PATH, CORRAL, APIARY, PLANTATION, PLANTGATE, ROADCURVE,
        EGATE, GARDEN, STORE, FEMRES, CHAPEL, RIVER, HALL, MALRES,
        SPORTS, FIELD, SMALLPLANT, WGATE, NEWSTORE, NEWCISTERN, SMALLCORRAL, COFFEE,
        NOEXIT;
    }
}