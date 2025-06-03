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
        MASTERHOUSE, MYHOUSE, WORKSHOP, MONASTERY, ELDERHOUSE,
        MOUNTBASE, MORIAH, FOREST, PATH, CORRAL, PLANTATION, PLANTGATE, ROADCURVE,
        EGATE, GARDEN, STORE, FEMRES, CHAPEL, RIVER, HALL, MALRES,
        SPORTS, FIELD, WGATE, NEWSTORE, NEWCISTERN, SMALLPLANT, SMALLCORRAL, COFFEE,
        NOEXIT;
    }
}