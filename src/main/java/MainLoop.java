import java.io.*;
import java.util.*;

/*
 * 
 * @author Arthur Oliveira Montenegro
 */

public class MainLoop {
    public static void main(String[] args) throws IOException {
        BufferedReader in;
        String input;

        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("> ");
            input = in.readLine();
            System.out.println("Input: " + input);
        } while (!"q".equals(input));
    }
}