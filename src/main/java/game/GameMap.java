package game;

import java.util.HashMap;
import java.util.ResourceBundle;

import gameobjects.Place;

public class GameMap {

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

    public static HashMap<PlaceName, Place> loadMap(ResourceBundle Language) {
        HashMap<PlaceName, Place> gameMap = new HashMap<>();
        // Add Places to the gameMap
        // gameMap.put(PlaceName, new Place(name, desc, n, s, e, w));
        gameMap.put(PlaceName.LEADERHOUSE, new Place(
                "leaderHouseName", "leaderHouseDesc", false,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.YOURHOUSE, PlaceName.PLANTATION));

        gameMap.put(PlaceName.YOURHOUSE, new Place(
                "yourHouseName", "yourHouseDesc", true,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.WORKSHOP, PlaceName.LEADERHOUSE));

        gameMap.put(PlaceName.WORKSHOP, new Place(
                "workshopName", "workshopDesc", true,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.MONASTERY, PlaceName.YOURHOUSE));

        gameMap.put(PlaceName.MONASTERY, new Place(
                "monasteryName", "monasteryDesc", true,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.ELDERHOUSE, PlaceName.WORKSHOP));

        gameMap.put(PlaceName.ELDERHOUSE, new Place(
                "elderHouseName", "elderHouseDesc", false,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.NOEXIT, PlaceName.MONASTERY));

        gameMap.put(PlaceName.MOUNTBASE, new Place(
                "mountBaseName", "mountBaseDesc", false,
                PlaceName.YOURHOUSE, PlaceName.MORIAH, PlaceName.NOEXIT, PlaceName.PLANTATION));

        gameMap.put(PlaceName.MORIAH, new Place(
                "moriahName", "moriahDesc", false,
                PlaceName.MOUNTBASE, PlaceName.FOREST, PlaceName.NOEXIT, PlaceName.PATH));

        gameMap.put(PlaceName.FOREST, new Place(
                "forestName", "forestDesc", false,
                PlaceName.MORIAH, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.PATH, new Place(
                "pathName", "pathDesc", false,
                PlaceName.NOEXIT, PlaceName.CORRAL, PlaceName.MORIAH, PlaceName.NOEXIT));

        gameMap.put(PlaceName.CORRAL, new Place(
                "corralName", "corralDesc", true,
                PlaceName.APIARY, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.ROADCURVE));

        gameMap.put(PlaceName.APIARY, new Place(
                "apiaryName", "apiaryDesc", false,
                PlaceName.PLANTATION, PlaceName.CORRAL, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.PLANTATION, new Place(
                "plantationName", "plantationDesc", false,
                PlaceName.LEADERHOUSE, PlaceName.APIARY, PlaceName.MOUNTBASE, PlaceName.PLANTGATE));

        gameMap.put(PlaceName.PLANTGATE, new Place(
                "plantGateName", "plantGateDesc", false,
                PlaceName.WGATE, PlaceName.ROADCURVE, PlaceName.PLANTATION, PlaceName.NOEXIT));

        gameMap.put(PlaceName.ROADCURVE, new Place(
                "roadCurveName", "roadCurveDesc", false,
                PlaceName.PLANTGATE, PlaceName.NOEXIT, PlaceName.CORRAL, PlaceName.NOEXIT));

        gameMap.put(PlaceName.EGATE, new Place(
                "eGateName", "eGateDesc", false,
                PlaceName.FEMRES, PlaceName.MONASTERY, PlaceName.GARDEN, PlaceName.HALL));

        gameMap.put(PlaceName.GARDEN, new Place(
                "gardenName", "gardenDesc", false,
                PlaceName.STORE, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.EGATE));

        gameMap.put(PlaceName.STORE, new Place(
                "storeName", "storeDesc", true,
                PlaceName.CHAPEL, PlaceName.GARDEN, PlaceName.NOEXIT, PlaceName.FEMRES));

        gameMap.put(PlaceName.FEMRES, new Place(
                "femResName", "femResDesc", true,
                PlaceName.CHAPEL, PlaceName.EGATE, PlaceName.STORE, PlaceName.SPORTS));

        gameMap.put(PlaceName.CHAPEL, new Place(
                "chapelName", "chapelDesc", true,
                PlaceName.RIVER, PlaceName.STORE, PlaceName.NOEXIT, PlaceName.SPORTS));

        gameMap.put(PlaceName.RIVER, new Place(
                "riverName", "riverDesc", false,
                PlaceName.NOEXIT, PlaceName.CHAPEL, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.HALL, new Place(
                "hallName", "hallDesc", true,
                PlaceName.SPORTS, PlaceName.NOEXIT, PlaceName.EGATE, PlaceName.MALRES));

        gameMap.put(PlaceName.MALRES, new Place(
                "malResName", "malResDesc", true,
                PlaceName.SPORTS, PlaceName.NOEXIT, PlaceName.HALL, PlaceName.WGATE));

        gameMap.put(PlaceName.SPORTS, new Place(
                "sportsName", "sportsDesc", false,
                PlaceName.FIELD, PlaceName.HALL, PlaceName.CHAPEL, PlaceName.NEWSTORE));

        gameMap.put(PlaceName.FIELD, new Place(
                "fieldName", "fieldDesc", false,
                PlaceName.RIVER, PlaceName.SPORTS, PlaceName.CHAPEL, PlaceName.SMALLPLANT));

        gameMap.put(PlaceName.SMALLPLANT, new Place(
                "smallPlantName", "smallPlantDesc", false,
                PlaceName.RIVER, PlaceName.SPORTS, PlaceName.FIELD, PlaceName.NEWCISTERN));

        gameMap.put(PlaceName.WGATE, new Place(
                "wGateName", "wGateDesc", false,
                PlaceName.NEWSTORE, PlaceName.PLANTGATE, PlaceName.MALRES, PlaceName.NOEXIT));

        gameMap.put(PlaceName.NEWSTORE, new Place(
                "newStoreName", "newStoreDesc", true,
                PlaceName.NEWCISTERN, PlaceName.WGATE, PlaceName.SPORTS, PlaceName.COFFEE));

        gameMap.put(PlaceName.NEWCISTERN, new Place(
                "newCisternName", "newCisternDesc", false,
                PlaceName.SMALLCORRAL, PlaceName.NEWSTORE, PlaceName.SMALLPLANT, PlaceName.COFFEE));

        gameMap.put(PlaceName.SMALLCORRAL, new Place(
                "smallCorralName", "smallCorralDesc", true,
                PlaceName.RIVER, PlaceName.NEWCISTERN, PlaceName.SMALLPLANT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.COFFEE, new Place(
                "coffeeName", "coffeeDesc", false,
                PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.NEWCISTERN, PlaceName.NOEXIT));
        return gameMap;
    }
}