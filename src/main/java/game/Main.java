package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
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

        // Language selection
        BufferedReader langIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose a language/ Escolhe uma língua/ Elige un idioma");
        System.out.println("English: en");
        System.out.println("Portuguese: pt");
        System.out.println("Español: es");
        System.out.print("> ");
        String langCode = langIn.readLine().trim();

        // Create a Locale with only the language
        Locale selectedLocale = new Locale(langCode);

        // Load ResourceBundle with just the language
        ResourceBundle Language;
        try {
            Language = ResourceBundle.getBundle("resources.Language", selectedLocale);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Language not supported. Falling back to English.");
            Language = ResourceBundle.getBundle("resources.Language", new Locale("en"));
        }

        // Start game
        Game game = new Game(Language);

        in = new BufferedReader(new InputStreamReader(System.in));

        game.showIntro();

        do {
            System.out.print("> ");
            input = in.readLine();
            switch (input) {
                case "quit":
                    running = false;
                    break;
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
                System.out.println(output);
            }
        } while (running);
    }
}