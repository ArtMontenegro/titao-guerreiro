package game;

import gameobjects.Actor;
import gameobjects.Map.Direction;
import gameobjects.Map.PlaceName;
import gameobjects.Place;

import java.text.MessageFormat;
import java.util.*;

public class Game {

    private ResourceBundle Language;

    // Load commands from Language files
    private final HashMap<String, Direction> commandMap = new HashMap<>();
    private void loadLocalizedCommands(ResourceBundle Language) {
        commandMap.clear();

        // Define which directions to look for
        HashMap<String, Direction> directionKeys = new HashMap<>();
        directionKeys.put("north", Direction.NORTH);
        directionKeys.put("south", Direction.SOUTH);
        directionKeys.put("east", Direction.EAST);
        directionKeys.put("west", Direction.WEST);

        for (Map.Entry<String, Direction> entry : directionKeys.entrySet()) {
            String dirKey = "cmd." + entry.getKey();
            if (Language.containsKey(dirKey)) {
                String[] aliases = Language.getString(dirKey).split("\\s*,\\s*");
                for (String alias : aliases) {
                    commandMap.put(alias.toLowerCase(), entry.getValue());
                }
            }
        }
    }

    // Load objects from Language files
    List<String> objects = new ArrayList<>(Arrays.asList("sword", "stick"));

    public HashMap<PlaceName, Place> gameMap;
    private Actor player;

    public Game(ResourceBundle Language) {
        this.Language = Language;
        loadLocalizedCommands(Language); // Loads localized commands
        gameMap = new HashMap<>();

        // Add Places to the gameMap
        // gameMap.put(PlaceName, new Place(name, desc, n, s, e, w));
        gameMap.put(PlaceName.LEADERHOUSE, new Place(
                Language.getString("leaderHouseName"), Language.getString("leaderHouseDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.YOURHOUSE, PlaceName.PLANTATION));

        gameMap.put(PlaceName.YOURHOUSE, new Place(
                Language.getString("yourHouseName"), Language.getString("yourHouseDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.WORKSHOP, PlaceName.LEADERHOUSE));

        gameMap.put(PlaceName.WORKSHOP, new Place(
                Language.getString("workshopName"), Language.getString("workshopDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.MONASTERY, PlaceName.YOURHOUSE));

        gameMap.put(PlaceName.MONASTERY, new Place(
                Language.getString("monasteryName"), Language.getString("monasteryDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.ELDERHOUSE, PlaceName.WORKSHOP));

        gameMap.put(PlaceName.ELDERHOUSE, new Place(
                Language.getString("elderHouseName"), Language.getString("elderHouseDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.NOEXIT, PlaceName.MONASTERY));

        gameMap.put(PlaceName.MOUNTBASE, new Place(
                Language.getString("mountBaseName"), Language.getString("mountBaseDesc"),
                PlaceName.YOURHOUSE, PlaceName.MORIAH, PlaceName.NOEXIT, PlaceName.PLANTATION));

        gameMap.put(PlaceName.MORIAH, new Place(
                Language.getString("moriahName"), Language.getString("moriahDesc"),
                PlaceName.MOUNTBASE, PlaceName.FOREST, PlaceName.NOEXIT, PlaceName.PATH));

        gameMap.put(PlaceName.FOREST, new Place(
                Language.getString("forestName"), Language.getString("forestDesc"),
                PlaceName.MORIAH, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.PATH, new Place(
                Language.getString("pathName"), Language.getString("pathDesc"),
                PlaceName.NOEXIT, PlaceName.CORRAL, PlaceName.MORIAH, PlaceName.NOEXIT));

        gameMap.put(PlaceName.CORRAL, new Place(
                Language.getString("corralName"), Language.getString("corralDesc"),
                PlaceName.APIARY, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.ROADCURVE));

        gameMap.put(PlaceName.APIARY, new Place(
                Language.getString("apiaryName"), Language.getString("apiaryDesc"),
                PlaceName.PLANTATION, PlaceName.CORRAL, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.PLANTATION, new Place(
                Language.getString("plantationName"), Language.getString("plantationDesc"),
                PlaceName.LEADERHOUSE, PlaceName.APIARY, PlaceName.MOUNTBASE, PlaceName.PLANTGATE));

        gameMap.put(PlaceName.PLANTGATE, new Place(
                Language.getString("plantGateName"), Language.getString("plantGateDesc"),
                PlaceName.WGATE, PlaceName.ROADCURVE, PlaceName.PLANTATION, PlaceName.NOEXIT));

        gameMap.put(PlaceName.ROADCURVE, new Place(
                Language.getString("roadCurveName"), Language.getString("roadCurveDesc"),
                PlaceName.PLANTGATE, PlaceName.NOEXIT, PlaceName.CORRAL, PlaceName.NOEXIT));

        gameMap.put(PlaceName.EGATE, new Place(
                Language.getString("eGateName"), Language.getString("eGateDesc"),
                PlaceName.FEMRES, PlaceName.MONASTERY, PlaceName.GARDEN, PlaceName.HALL));

        gameMap.put(PlaceName.GARDEN, new Place(
                Language.getString("gardenName"), Language.getString("gardenDesc"),
                PlaceName.STORE, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.EGATE));

        gameMap.put(PlaceName.STORE, new Place(
                Language.getString("storeName"), Language.getString("storeDesc"),
                PlaceName.CHAPEL, PlaceName.GARDEN, PlaceName.NOEXIT, PlaceName.FEMRES));

        gameMap.put(PlaceName.FEMRES, new Place(
                Language.getString("femResName"), Language.getString("femResDesc"),
                PlaceName.CHAPEL, PlaceName.EGATE, PlaceName.STORE, PlaceName.SPORTS));

        gameMap.put(PlaceName.CHAPEL, new Place(
                Language.getString("chapelName"), Language.getString("chapelDesc"),
                PlaceName.RIVER, PlaceName.STORE, PlaceName.NOEXIT, PlaceName.SPORTS));

        gameMap.put(PlaceName.RIVER, new Place(
                Language.getString("riverName"), Language.getString("riverDesc"),
                PlaceName.NOEXIT, PlaceName.CHAPEL, PlaceName.NOEXIT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.HALL, new Place(
                Language.getString("hallName"), Language.getString("hallDesc"),
                PlaceName.SPORTS, PlaceName.NOEXIT, PlaceName.EGATE, PlaceName.MALRES));

        gameMap.put(PlaceName.MALRES, new Place(
                Language.getString("malResName"), Language.getString("malResDesc"),
                PlaceName.SPORTS, PlaceName.NOEXIT, PlaceName.HALL, PlaceName.WGATE));

        gameMap.put(PlaceName.SPORTS, new Place(
                Language.getString("sportsName"), Language.getString("sportsDesc"),
                PlaceName.FIELD, PlaceName.HALL, PlaceName.CHAPEL, PlaceName.NEWSTORE));

        gameMap.put(PlaceName.FIELD, new Place(
                Language.getString("fieldName"), Language.getString("fieldDesc"),
                PlaceName.RIVER, PlaceName.SPORTS, PlaceName.CHAPEL, PlaceName.SMALLPLANT));

        gameMap.put(PlaceName.SMALLPLANT, new Place(
                Language.getString("smallPlantName"), Language.getString("smallPlantDesc"),
                PlaceName.RIVER, PlaceName.SPORTS, PlaceName.FIELD, PlaceName.NEWCISTERN));

        gameMap.put(PlaceName.WGATE, new Place(
                Language.getString("wGateName"), Language.getString("wGateDesc"),
                PlaceName.NEWSTORE, PlaceName.PLANTGATE, PlaceName.MALRES, PlaceName.NOEXIT));

        gameMap.put(PlaceName.NEWSTORE, new Place(
                Language.getString("newStoreName"), Language.getString("newStoreDesc"),
                PlaceName.NEWCISTERN, PlaceName.WGATE, PlaceName.SPORTS, PlaceName.COFFEE));

        gameMap.put(PlaceName.NEWCISTERN, new Place(
                Language.getString("newCisternName"), Language.getString("newCisternDesc"),
                PlaceName.SMALLCORRAL, PlaceName.NEWSTORE, PlaceName.SMALLPLANT, PlaceName.COFFEE));

        gameMap.put(PlaceName.SMALLCORRAL, new Place(
                Language.getString("smallCorralName"), Language.getString("smallCorralDesc"),
                PlaceName.RIVER, PlaceName.NEWCISTERN, PlaceName.SMALLPLANT, PlaceName.NOEXIT));

        gameMap.put(PlaceName.COFFEE, new Place(
                Language.getString("coffeeName"), Language.getString("coffeeDesc"),
                PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.NEWCISTERN, PlaceName.NOEXIT));

        player = new Actor("player", "placeholder", gameMap.get(PlaceName.YOURHOUSE));
    }

    // Accessor methods
    public HashMap<PlaceName, Place> getGameMap() {
        return gameMap;
    }
    public void setGameMap(HashMap<PlaceName, Place> aGameMap) {
        gameMap = aGameMap;
    }

    public Actor getPlayer() {
        return player;
    }
    public void setPlayer(Actor aPlayer) {
        player = aPlayer;
    }

    // Move Actor to Place
    private void moveToPlace(Actor actor, Place aPlace) {
        actor.setLocation(aPlace);
    }
    // Move Actor to direction
    private PlaceName moveTo(Actor anActor, Direction dir) {
        Place nowLoc = anActor.getLocation();
        PlaceName exit;

        switch (dir) {
            case NORTH:
                exit = nowLoc.getN();
                break;
            case SOUTH:
                exit = nowLoc.getS();
                break;
            case EAST:
                exit = nowLoc.getE();
                break;
            case WEST:
                exit = nowLoc.getW();
                break;
            default:
                exit = Direction.NOEXIT;
                break;
        }
        if (exit != Direction.NOEXIT) {
            moveToPlace(anActor, gameMap.get(exit));
        }
        return exit;
    }
    // Move Player
    public PlaceName movePlayerTo(Direction dir) {
        return moveTo(player, dir);
    }
    // Print text after player moves
    private String updateOutput(PlaceName placeName) {
        String output;
        if (placeName == PlaceName.NOEXIT) {
            output = Language.getString("noExitMsg");
        } else {
            Place place = getPlayer().getLocation();
            output = MessageFormat.format(Language.getString("locationMsg"), place.getName());
        }
        return output;
    }

    // Interpret input
    public String takeCommand(List<String> wordList) {
        if (wordList.isEmpty()) return "";

        String cmd = wordList.get(0).toLowerCase();

        // Help cmd
        if (cmd.equals(Language.getString("cmd.help"))) {
            return Language.getString("helpMsg");
        }

        // Direction cmds
        Direction dir = commandMap.get(cmd);
        if (dir != null) {
            return updateOutput(movePlayerTo(dir));
        }

        // Look cmd
        if (cmd.equals(Language.getString("cmd.look"))) {
            Place currentPlace = getPlayer().getLocation();
            String placeName = currentPlace.getName();
            String placeDesc = currentPlace.getDesc();

            return placeName + ", " + placeDesc;
        }

        return Language.getString("dontUnderstandMsg") + " " + cmd;
    }
    // Word list maker
    public static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> stringList = new ArrayList<>();
        String[] words = input.split(delims);
        Collections.addAll(stringList, words);
        return stringList;
    }
    public String runCommand(String inputStr) {
        List<String> wordList;
        String output;
        String lowStr = inputStr.trim().toLowerCase();
        wordList = wordList(lowStr);
        output = takeCommand(wordList);

        // Capitalize first letter of output if not empty
        if (!output.isEmpty()) {
            output = output.substring(0, 1).toUpperCase() + output.substring(1);
        }

        return output;
    }

    public void showIntro() {
        System.out.println();
        System.out.println(Language.getString("introMsg"));
        System.out.println();
        System.out.println(Language.getString("helpMsg"));
    }
}