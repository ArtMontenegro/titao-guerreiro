package game;

import gameobjects.Actor;
import gameobjects.GameMap.Direction;
import gameobjects.GameMap.PlaceName;
import gameobjects.Place;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class CommandHandler {

    private ResourceBundle Language;
    private Game game;
    private Map<PlaceName, Place> gameMap;
    private Actor player;

    public CommandHandler(ResourceBundle Language, Game game) {
        this.Language = Language;
        this.game = game;
        this.gameMap = game.gameMap;
        this.player = game.getPlayer();
        loadLocalizedCommands(Language);
    }

    public void setLanguage(ResourceBundle newLanguage) {
        this.Language = newLanguage;
        loadLocalizedCommands(newLanguage);
    }

    // Command (String) -> Direction map for movement commands
    private final Map<String, Direction> commandMap = new HashMap<>();

    // Load movement command aliases from language file
    private void loadLocalizedCommands(ResourceBundle Language) {
        commandMap.clear();

        Map<String, Direction> directionKeys = new HashMap<>();
        directionKeys.put("north", Direction.NORTH);
        directionKeys.put("south", Direction.SOUTH);
        directionKeys.put("east", Direction.EAST);
        directionKeys.put("west", Direction.WEST);

        for (Map.Entry<String, Direction> entry : directionKeys.entrySet()) {
            String key = "cmd." + entry.getKey();
            if (Language.containsKey(key)) {
                String[] aliases = Language.getString(key).split("\\s*,\\s*");
                for (String alias : aliases) {
                    commandMap.put(alias.toLowerCase(Locale.ROOT), entry.getValue());
                }
            }
        }
    }

    // Interpret input
    public String takeCommand(List<String> wordList) {
        if (wordList.isEmpty())
            return "";

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
            Place currentPlace = game.getPlayer().getLocation();
            String placeName = currentPlace.getName(Language);
            String placeDesc = currentPlace.getDesc(Language);

            return placeName + ", " + placeDesc;
        }

        return Language.getString("dontUnderstandMsg") + " " + cmd;
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
            case NORTH: exit = nowLoc.getN(); break;
            case SOUTH: exit = nowLoc.getS(); break;
            case EAST: exit = nowLoc.getE(); break;
            case WEST: exit = nowLoc.getW(); break;
            default: exit = Direction.NOEXIT; break;
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

    // Print text after player moves
    private String updateOutput(PlaceName placeName) {
        String output;
        if (placeName == PlaceName.NOEXIT) {
            output = Language.getString("noExitMsg");
        } else {
            Place place = game.getPlayer().getLocation();
            output = MessageFormat.format(Language.getString("locationMsg"), place.getName(Language));
        }
        return output;
    }

    // Word list maker
    public static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> stringList = new ArrayList<>();
        String[] words = input.split(delims);
        Collections.addAll(stringList, words);
        return stringList;
    }
}