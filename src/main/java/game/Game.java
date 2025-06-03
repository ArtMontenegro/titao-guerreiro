package game;

import gameobjects.Actor;
import gameobjects.Map.Direction;
import gameobjects.Map.PlaceName;
import gameobjects.Place;

import java.util.*;

public class Game {

    public HashMap<PlaceName, Place> map;
    private Actor player;
    List<String> commands = new ArrayList<>(Arrays.asList("n", "s", "e", "l", "w", "o", "take", "drop"));
    //List<String> objects = new ArrayList<>(Arrays.asList("sword", "stick"));

    public Game() {
        map = new HashMap<>();

        // Add Places to the map
        // map.put(PlaceName, new Place(name, desc, n, s, e, w));
        map.put(PlaceName.MASTERHOUSE, new Place("master house", "placeholder", PlaceName.EGATE, PlaceName.MOUNTBASE,
                PlaceName.MYHOUSE, PlaceName.NOEXIT));
        map.put(PlaceName.MYHOUSE, new Place("my house", "placeholder", PlaceName.WORKSHOP, PlaceName.MOUNTBASE,
                PlaceName.WORKSHOP, PlaceName.MASTERHOUSE));

        player = new Actor("player", "placeholder", map.get(PlaceName.MYHOUSE));
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
        if (placeName == Direction.NOEXIT) {
            output = "No Exit!";
        } else {
            Place place = getPlayer().getLocation();
            output = "You are in " + place.getName() + ". " + place.getDesc();
        }
        System.out.println(output);
    }
    // Call the functions with direction as parameter
    private void goN() {
        updateOutput(movePlayerTo(Direction.NORTH));
    }
    private void goS() {
        updateOutput(movePlayerTo(Direction.SOUTH));
    }
    private void goW() {
        updateOutput(movePlayerTo(Direction.WEST));
    }
    private void goE() {
        updateOutput(movePlayerTo(Direction.EAST));
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
                    goN();
                    break;
                case "s":
                    goS();
                    break;
                case "e":
                case "l":
                    goE();
                    break;
                case "w":
                case "o":
                    goW();
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
        String s;
        s = "O Guerreiro Titao"; // Placeholder intro
        System.out.println(s);
    }
}