package gameobjects;

import java.util.HashMap;
import java.util.ResourceBundle;

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
                Language.getString("leaderHouseName"), Language.getString("leaderHouseDesc"), false,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.YOURHOUSE, PlaceName.PLANTATION));

        gameMap.put(PlaceName.YOURHOUSE, new Place(
                Language.getString("yourHouseName"), Language.getString("yourHouseDesc"), true,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.WORKSHOP, PlaceName.LEADERHOUSE));

        gameMap.put(PlaceName.WORKSHOP, new Place(
                Language.getString("workshopName"), Language.getString("workshopDesc"), true,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.MONASTERY, PlaceName.YOURHOUSE));

        gameMap.put(PlaceName.MONASTERY, new Place(
                Language.getString("monasteryName"), Language.getString("monasteryDesc"), true,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.ELDERHOUSE, PlaceName.WORKSHOP));

        gameMap.put(PlaceName.ELDERHOUSE, new Place(
                Language.getString("elderHouseName"), Language.getString("elderHouseDesc"), false,
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.NOEXIT, PlaceName.MONASTERY));

        gameMap.put(PlaceName.MOUNTBASE, new Place(
                Language.getString("mountBaseName"), Language.getString("mountBaseDesc"), false,
                PlaceName.YOURHOUSE, PlaceName.MORIAH, PlaceName.NOEXIT, PlaceName.PLANTATION));

        gameMap.put(PlaceName.MORIAH, new Place(
                Language.getString("moriahName"), Language.getString("moriahDesc"), false,
                PlaceName.MOUNTBASE, PlaceName.FOREST, PlaceName.NOEXIT, PlaceName.PATH));

        gameMap.put(PlaceName.FOREST, new Place(
                Language.getString("forestName"), Language.getString("forestDesc"), false,
                PlaceName.MORIAH, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.PATH, new Place(
                Language.getString("pathName"), Language.getString("pathDesc"), false,
                PlaceName.NOEXIT, PlaceName.CORRAL, PlaceName.MORIAH, PlaceName.NOEXIT));

        gameMap.put(PlaceName.CORRAL, new Place(
                Language.getString("corralName"), Language.getString("corralDesc"), true,
                PlaceName.APIARY, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.ROADCURVE));

        gameMap.put(PlaceName.APIARY, new Place(
                Language.getString("apiaryName"), Language.getString("apiaryDesc"), false,
                PlaceName.PLANTATION, PlaceName.CORRAL, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.PLANTATION, new Place(
                Language.getString("plantationName"), Language.getString("plantationDesc"), false,
                PlaceName.LEADERHOUSE, PlaceName.APIARY, PlaceName.MOUNTBASE, PlaceName.PLANTGATE));

        gameMap.put(PlaceName.PLANTGATE, new Place(
                Language.getString("plantGateName"), Language.getString("plantGateDesc"), false,
                PlaceName.WGATE, PlaceName.ROADCURVE, PlaceName.PLANTATION, PlaceName.NOEXIT));

        gameMap.put(PlaceName.ROADCURVE, new Place(
                Language.getString("roadCurveName"), Language.getString("roadCurveDesc"), false,
                PlaceName.PLANTGATE, PlaceName.NOEXIT, PlaceName.CORRAL, PlaceName.NOEXIT));

        gameMap.put(PlaceName.EGATE, new Place(
                Language.getString("eGateName"), Language.getString("eGateDesc"), false,
                PlaceName.FEMRES, PlaceName.MONASTERY, PlaceName.GARDEN, PlaceName.HALL));

        gameMap.put(PlaceName.GARDEN, new Place(
                Language.getString("gardenName"), Language.getString("gardenDesc"), false,
                PlaceName.STORE, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.EGATE));

        gameMap.put(PlaceName.STORE, new Place(
                Language.getString("storeName"), Language.getString("storeDesc"), true,
                PlaceName.CHAPEL, PlaceName.GARDEN, PlaceName.NOEXIT, PlaceName.FEMRES));

        gameMap.put(PlaceName.FEMRES, new Place(
                Language.getString("femResName"), Language.getString("femResDesc"), true,
                PlaceName.CHAPEL, PlaceName.EGATE, PlaceName.STORE, PlaceName.SPORTS));

        gameMap.put(PlaceName.CHAPEL, new Place(
                Language.getString("chapelName"), Language.getString("chapelDesc"), true,
                PlaceName.RIVER, PlaceName.STORE, PlaceName.NOEXIT, PlaceName.SPORTS));

        gameMap.put(PlaceName.RIVER, new Place(
                Language.getString("riverName"), Language.getString("riverDesc"), false,
                PlaceName.NOEXIT, PlaceName.CHAPEL, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.HALL, new Place(
                Language.getString("hallName"), Language.getString("hallDesc"), true,
                PlaceName.SPORTS, PlaceName.NOEXIT, PlaceName.EGATE, PlaceName.MALRES));

        gameMap.put(PlaceName.MALRES, new Place(
                Language.getString("malResName"), Language.getString("malResDesc"), true,
                PlaceName.SPORTS, PlaceName.NOEXIT, PlaceName.HALL, PlaceName.WGATE));

        gameMap.put(PlaceName.SPORTS, new Place(
                Language.getString("sportsName"), Language.getString("sportsDesc"), false,
                PlaceName.FIELD, PlaceName.HALL, PlaceName.CHAPEL, PlaceName.NEWSTORE));

        gameMap.put(PlaceName.FIELD, new Place(
                Language.getString("fieldName"), Language.getString("fieldDesc"), false,
                PlaceName.RIVER, PlaceName.SPORTS, PlaceName.CHAPEL, PlaceName.SMALLPLANT));

        gameMap.put(PlaceName.SMALLPLANT, new Place(
                Language.getString("smallPlantName"), Language.getString("smallPlantDesc"), false,
                PlaceName.RIVER, PlaceName.SPORTS, PlaceName.FIELD, PlaceName.NEWCISTERN));

        gameMap.put(PlaceName.WGATE, new Place(
                Language.getString("wGateName"), Language.getString("wGateDesc"), false,
                PlaceName.NEWSTORE, PlaceName.PLANTGATE, PlaceName.MALRES, PlaceName.NOEXIT));

        gameMap.put(PlaceName.NEWSTORE, new Place(
                Language.getString("newStoreName"), Language.getString("newStoreDesc"), true,
                PlaceName.NEWCISTERN, PlaceName.WGATE, PlaceName.SPORTS, PlaceName.COFFEE));

        gameMap.put(PlaceName.NEWCISTERN, new Place(
                Language.getString("newCisternName"), Language.getString("newCisternDesc"), false,
                PlaceName.SMALLCORRAL, PlaceName.NEWSTORE, PlaceName.SMALLPLANT, PlaceName.COFFEE));

        gameMap.put(PlaceName.SMALLCORRAL, new Place(
                Language.getString("smallCorralName"), Language.getString("smallCorralDesc"), true,
                PlaceName.RIVER, PlaceName.NEWCISTERN, PlaceName.SMALLPLANT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.COFFEE, new Place(
                Language.getString("coffeeName"), Language.getString("coffeeDesc"), false,
                PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.NEWCISTERN, PlaceName.NOEXIT));
        return gameMap;
    }
}