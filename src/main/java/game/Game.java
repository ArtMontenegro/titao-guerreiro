package game;

import gameobjects.Actor;
import gameobjects.GameMap;
import gameobjects.GameMap.Direction;
import gameobjects.GameMap.PlaceName;
import gameobjects.Place;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

        gameMap = GameMap.loadMap(Language);

        player = new Actor("player", "placeholder", gameMap.get(PlaceName.YOURHOUSE));
    }

    // Player accessor methods
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

    // Change Language
    private String changeLanguage(String langCode) {
            if (!LanguageHandler.isSupported(langCode)) {
                    return "Invalid language code.";
            }

            LanguageHandler.setLanguage(langCode);
            this.Language = LanguageHandler.getBundle();
            loadLocalizedCommands(this.Language); // Update direction aliases

            return Language.getString("langChangedMsg");
    }

    // Interpret input
    public String takeCommand(List<String> wordList) {
        if (wordList.isEmpty()) return "";

        String cmd = wordList.get(0).toLowerCase();

        // Change language
        String[] langAliases = Language.getString("cmd.lang").split("\\s*,\\s*");
        for (String alias : langAliases) {
                if (cmd.equals(alias.toLowerCase()) && wordList.size() > 1) {
                        String newLang = wordList.get(1).toLowerCase();
                        return changeLanguage(newLang);
                }
        }

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