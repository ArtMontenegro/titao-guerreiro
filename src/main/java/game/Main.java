package game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 
 * @author Arthur Oliveira Montenegro
 */

public class Main {

    Static Game game;
    public static void main(String[] args) throws IOException {
        Boolean running = true;
        BufferedReader in;
        String input;
        String output = "";

        in = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.print("> ");
            input = in.readLine();
            switch (input) {
                case "quit":
                    running = false;
                    break;
                case "save":
                    saveGame();
                case "load":
                    loadGame();
                default:
                    output = runCommand(input);
                    break;
            }
            if (!output.trim().isEmpty()) {
                System.out.println(output);
            }
        } while (running);
    }

    private static void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("Adv.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
            System.out.print("\n---Game loaded---\n");
        } catch (Exception e) {
            System.out.print("Serialization Error! Can't load data.\n");
            System.out.print(e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    public static void parseCommand(List<String> wordList) {
        String command;
        String object;
        List<String> commands = new ArrayList<>(Arrays.asList("take", "drop"));
        List<String> objects = new ArrayList<>(Arrays.asList("sword", "stick"));

        if (wordList.size() != 2) {
            System.out.println("Por favor, use somente duas palavras");
        } else {
            command = wordList.get(0);
            object = wordList.get(1);
            if (!commands.contains(command)) {
                System.out.println("Não conheço este comando");
            }
            if (!objects.contains(object)) {
                System.out.println("Não conheço este objeto");
            }
        }
    }

    public static String runCommand(String inputstr) {
        List<String> wl;
        String lowstr = inputstr.trim().toLowerCase();
        String out = "PLACEHOLDER";

        if (!lowstr.equals("q") && !lowstr.equals("quit")) {
            wl = wordList(lowstr);
            parseCommand(wl);
        }
        return out;
    }

    private static void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("Adv.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game); // game
            oos.flush(); // write out any buffered bytes
            oos.close();
            System.out.print("Game saved\n");
        } catch (Exception e) {
            System.out.print("Serialization Error! Can't save data.\n"
                    + e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    public static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = input.split(delims);

        for (String word : words) {
            strlist.add(word);
        }
        return strlist;
    }
}