package game;

import gameobjects.Actor;
import gameobjects.Map.Direction;
import gameobjects.Map.PlaceName;
import gameobjects.Place;

import java.text.MessageFormat;
import java.util.*;

public class Game {

    private ResourceBundle Language;
    public HashMap<PlaceName, Place> map;
    private Actor player;
    List<String> commands = new ArrayList<>(Arrays.asList("n", "s", "e", "l", "w", "o"));
    List<String> objects = new ArrayList<>(Arrays.asList("sword", "stick"));

    public Game(ResourceBundle Language) {
        this.Language = Language;
        map = new HashMap<>();

        // Add Places to the map
        // map.put(PlaceName, new Place(name, desc, n, s, e, w));
        map.put(PlaceName.MASTERHOUSE, new Place(Language.getString("masterHouseName"), Language.getString("masterHouseDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.YOURHOUSE, PlaceName.PLANTATION));
        map.put(PlaceName.YOURHOUSE, new Place(Language.getString("yourHouseName"), Language.getString("yourHouseDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.WORKSHOP, PlaceName.MASTERHOUSE));
        map.put(PlaceName.WORKSHOP, new Place(Language.getString("workshopName"), Language.getString("workshopDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.MONASTERY, PlaceName.YOURHOUSE));
        map.put(PlaceName.MONASTERY, new Place(Language.getString("monasteryName"), Language.getString("monasteryDesc"),
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.ELDERHOUSE, PlaceName.WORKSHOP));
        map.put(PlaceName.ELDERHOUSE, new Place("elder's house", "placeholder",
                PlaceName.EGATE, PlaceName.MOUNTBASE, PlaceName.NOEXIT, PlaceName.MONASTERY));
        map.put(PlaceName.MOUNTBASE, new Place("mount moriah base", "placeholder",
                PlaceName.YOURHOUSE, PlaceName.MORIAH, PlaceName.NOEXIT, PlaceName.PLANTATION));
        map.put(PlaceName.MORIAH, new Place("moriah", "placeholder",
                PlaceName.MOUNTBASE, PlaceName.FOREST, PlaceName.NOEXIT, PlaceName.PATH));

        map.put(PlaceName.FOREST, new Place("forest", "placeholder",
                PlaceName.MORIAH, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.NOEXIT));
        map.put(PlaceName.PATH, new Place("path", "placeholder",
                PlaceName.NOEXIT, PlaceName.CORRAL, PlaceName.MORIAH, PlaceName.NOEXIT));
        map.put(PlaceName.CORRAL, new Place("corral", "placeholder",
                PlaceName.APIARY, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.ROADCURVE));
        map.put(PlaceName.APIARY, new Place("bees", "placeholder",
                PlaceName.PLANTATION, PlaceName.CORRAL, PlaceName.NOEXIT, PlaceName.NOEXIT));
        map.put(PlaceName.PLANTATION, new Place("plantation", "placeholder",
                PlaceName.MASTERHOUSE, PlaceName.APIARY, PlaceName.MOUNTBASE, PlaceName.PLANTGATE));
        map.put(PlaceName.PLANTGATE, new Place("plantation gate", "placeholder",
                PlaceName.WGATE, PlaceName.ROADCURVE, PlaceName.PLANTATION, PlaceName.NOEXIT));
        map.put(PlaceName.ROADCURVE, new Place("road curve", "placeholder",
                PlaceName.PLANTGATE, PlaceName.NOEXIT, PlaceName.CORRAL, PlaceName.NOEXIT));

        map.put(PlaceName.EGATE, new Place("eastern gate", "placeholder",
                PlaceName.FEMRES, PlaceName.MONASTERY, PlaceName.GARDEN, PlaceName.HALL));
        map.put(PlaceName.GARDEN, new Place("garden", "placeholder",
                PlaceName.STORE, PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.EGATE));
        map.put(PlaceName.STORE, new Place("store", "placeholder",
                PlaceName.CHAPEL, PlaceName.GARDEN, PlaceName.NOEXIT, PlaceName.FEMRES));
        map.put(PlaceName.FEMRES, new Place("female residence", "placeholder",
                PlaceName.CHAPEL, PlaceName.EGATE, PlaceName.STORE, PlaceName.SPORTS));
        map.put(PlaceName.CHAPEL, new Place("chapel", "placeholder",
                PlaceName.RIVER, PlaceName.STORE, PlaceName.NOEXIT, PlaceName.SPORTS));
        map.put(PlaceName.RIVER, new Place("river", "placeholder",
                PlaceName.NOEXIT, PlaceName.CHAPEL, PlaceName.NOEXIT, PlaceName.NOEXIT));
        map.put(PlaceName.HALL, new Place("hall", "placeholder",
                PlaceName.SPORTS, PlaceName.NOEXIT, PlaceName.EGATE, PlaceName.MALRES));
        map.put(PlaceName.MALRES, new Place("male residence", "placeholder",
                PlaceName.SPORTS, PlaceName.NOEXIT, PlaceName.HALL, PlaceName.WGATE));

        map.put(PlaceName.SPORTS, new Place("sports area", "placeholder",
                PlaceName.FIELD, PlaceName.HALL, PlaceName.CHAPEL, PlaceName.NEWSTORE));
        map.put(PlaceName.FIELD, new Place("football field", "placeholder",
                PlaceName.RIVER, PlaceName.SPORTS, PlaceName.CHAPEL, PlaceName.SMALLPLANT));
        map.put(PlaceName.SMALLPLANT, new Place("small plantation", "placeholder",
                PlaceName.RIVER, PlaceName.SPORTS, PlaceName.FIELD, PlaceName.NEWCISTERN));
        map.put(PlaceName.WGATE, new Place("western gate", "placeholder",
                PlaceName.NEWSTORE, PlaceName.PLANTGATE, PlaceName.MALRES, PlaceName.NOEXIT));
        map.put(PlaceName.NEWSTORE, new Place("new store", "placeholder",
                PlaceName.NEWCISTERN, PlaceName.WGATE, PlaceName.SPORTS, PlaceName.COFFEE));
        map.put(PlaceName.NEWCISTERN, new Place("new cistern", "placeholder",
                PlaceName.SMALLCORRAL, PlaceName.NEWSTORE, PlaceName.SMALLPLANT, PlaceName.COFFEE));
        map.put(PlaceName.SMALLCORRAL, new Place("small corral", "placeholder",
                PlaceName.RIVER, PlaceName.NEWCISTERN, PlaceName.SMALLPLANT, PlaceName.NOEXIT));
        map.put(PlaceName.COFFEE, new Place("coffee plantation", "placeholder",
                PlaceName.NOEXIT, PlaceName.NOEXIT, PlaceName.NEWCISTERN, PlaceName.NOEXIT));

        player = new Actor("player", "placeholder", map.get(PlaceName.YOURHOUSE));
    }

    // Accessor methods
    public HashMap<PlaceName, Place> getMap() {
        return map;
    }
    public void setMap(HashMap<PlaceName, Place> aMap) {
        map = aMap;
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
            moveToPlace(anActor, map.get(exit));
        }
        return exit;
    }
    // Move Player
    public PlaceName movePlayerTo(Direction dir) {
        return moveTo(player, dir);
    }
    // Print text after player moves
    private void updateOutput(PlaceName placeName) {
        String output;
        if (placeName == PlaceName.NOEXIT) {
            output = "No Exit!";
        } else {
            Place place = getPlayer().getLocation();
            output = MessageFormat.format(Language.getString("locationMsg"), place.getName());
        }
        System.out.println(output);
    }

    // Interpret input
    public String takeCommand(List<String> wordList) {
        String cmd;
        String msg = "";

        cmd = wordList.get(0);
        if (!commands.contains(cmd)) {
            msg = "I don't understand " + cmd;
        } else {
            switch (cmd) {
                case "n":
                    updateOutput(movePlayerTo(Direction.NORTH));
                    break;
                case "s":
                    updateOutput(movePlayerTo(Direction.SOUTH));
                    break;
                case "e":
                case "l":
                    updateOutput(movePlayerTo(Direction.EAST));
                    break;
                case "w":
                case "o":
                    updateOutput(movePlayerTo(Direction.WEST));
                    break;
                default:
                    msg = cmd + "can't do that yet";
                    break;
            }
        }
        return msg;
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
        return output;
    }

    public void showIntro() {
        String string;
        string = "O Guerreiro Titao"; // Placeholder intro
        System.out.println(string);
    }
}