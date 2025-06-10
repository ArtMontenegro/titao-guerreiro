package game;

import gameobjects.Actor;
import gameobjects.GameMap;
import gameobjects.GameMap.PlaceName;
import gameobjects.Place;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class Game {

    private ResourceBundle Language;
    private CommandHandler commandHandler;
    public HashMap<PlaceName, Place> gameMap;
    private Actor player;

    public Game(ResourceBundle Language) {
        this.Language = Language;
        gameMap = GameMap.loadMap(Language);
        player = new Actor("playerName", "playerDesc", gameMap.get(PlaceName.YOURHOUSE));
        commandHandler = new CommandHandler(Language, this);
    }

    // Player accessor methods
    public Actor getPlayer() {
        return player;
    }
    public void setPlayer(Actor aPlayer) {
        player = aPlayer;
    }

    public String runCommand(String inputStr) {
        List<String> wordList = CommandHandler.wordList(inputStr.trim().toLowerCase());
        String output = commandHandler.takeCommand(wordList); // <-- FIX
    
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