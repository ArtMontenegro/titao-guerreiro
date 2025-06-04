package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        Game game = new Game();

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
                    output = "saving..";
                    break;
                case "load":
                    output = "loading..";
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