package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.MissingResourceException;
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
        String langCode = "en"; // Defaults to english
        
        // Check if language code is passed as an argument
        if (args.length > 0 && !args[0].trim().isEmpty()) {
            langCode = args[0].trim();
        } else {
            try {
                System.out.println();
                System.out.println("Choose a language/ Escolhe uma língua/ Elige un idioma");
                System.out.println();
                System.out.println("English: en");
                System.out.println("Portuguese: pt");
                System.out.println("Español: es");
                System.out.print("> ");

                // Read user input for language
                String inputCode = langIn.readLine();
                if (inputCode != null && !inputCode.trim().isEmpty()) {
                    langCode = inputCode.trim();
                }
            } catch (IOException e) {
                System.out.println("Error reading input. Defaulting to English.");
            }
        }

        // Create a Locale with only the language
        Locale selectedLocale = new Locale(langCode);

        // Load ResourceBundle with UTF-8 support
        ResourceBundle Language;
        try {
            Language = ResourceBundle.getBundle("Language", selectedLocale, new UTF8Control());
        } catch (MissingResourceException e) {
            System.err.println("Missing resource bundle: " + e.getMessage());
            System.out.println("Language not supported. Falling back to English.");
            Language = ResourceBundle.getBundle("Language", new Locale("en"), new UTF8Control());
        }

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