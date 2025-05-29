import java.io.*;
import java.util.*;

/*
 * 
 * @author Arthur Oliveira Montenegro
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Boolean running = true;
        BufferedReader in;
        String input;
        String output;
        List<String> escape = new ArrayList<>(Arrays.asList("q", "quit"));

        in = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.print("> ");
            input = in.readLine();

            // check if input is escape sequence
            if (escape.contains(input)) {
                running = false;
            }

            output = runCommand(input);
            System.out.println(output);
        } while (running);
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

    public static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = input.split(delims);

        for (String word : words) {
            strlist.add(word);
        }
        return strlist;
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
}