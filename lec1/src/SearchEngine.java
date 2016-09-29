import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by maau on 27/09/16.
 * Modifed by Lucas to test github
 */
public class SearchEngine {
    public static void main(String[] args) {
        System.out.println("Welcome to the Search Engine");

        // checks to see if the user provided only one argument
        if (args.length != 1) {
            System.out.println("Error: Please provide a filename <filename>");
            return;
        }

        List<Website> list = FileHelper.parseFile(args[0]); //initialize the list
        System.out.println("Please enter a word");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            // reads each entry and stops if the entry is "quit"
            String line = sc.next();
            if (line.equals("quit"))
            {
                System.out.println("program exited successfully");
                return;
            }
            // Search for line in the list of websites.
            for (Website w: list) {
                if (w.containsWord(line)) {
                    System.out.println("Word found on " + w.getUrl());
                }
            }
        }

    }
}
