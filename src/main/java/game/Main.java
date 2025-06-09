package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/*
 * 
 * @author Arthur Oliveira Montenegro
 */

public class Main {
    
    public static void main(String[] args) throws IOException {
        boolean running = true;
        BufferedReader in;
        String input;
        String output = "";

        // Initialize language settings
        LanguageHandler.loadLanguage(args);
        ResourceBundle Language = LanguageHandler.getBundle();

        // Start game
        Game game = new Game(Language);
        in = new BufferedReader(new InputStreamReader(System.in));
        game.showIntro();

        // Main loop
        do {
            System.out.print("> ");
            input = in.readLine();
            if (input == null) {
                System.out.println("\n" + Language.getString("inputClosedMsg"));
                break;
            }

            input = input.trim();
            if (input.isEmpty()) {
                continue;
            }

            switch (input.toLowerCase()) {
                case "quit":
                    running = false;
                    continue;
                case "save":
                    output = Language.getString("savingMsg");
                    break;
                case "load":
                    output = Language.getString("loadingMsg");
                    break;
                default:
                    output = game.runCommand(input);
                    break;
            }

            if (!output.trim().isEmpty()) {
                System.out.println();
                System.out.println(output);
            }
        } while (running);
    }
}