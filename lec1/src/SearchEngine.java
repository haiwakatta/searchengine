import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by maau on 27/09/16.
 * Modifed by Lucas to test github
 *
 * @author Martin Aumüller
 * modified by Lucas
 * modified by Hao
 */
public class SearchEngine {
    public static void main(String[] args)
    {
        // prints user's instructions
        System.out.println("Welcome to the Search Engine");
        System.out.println("The available search methods are 1: 'Simple', 2: 'TreeMap', 3:'HashMap' ");
        System.out.println("To search for a word, just type the search type index and then the word");
        System.out.println("Example: '2 banana'");
        System.out.println("To exit the program, just type quit");

        // checks to see if the user provided only one argument
        if (args.length != 1) {
            System.out.println("Error: Please provide a filename <filename>");
            return;
        }

        List<Website> list = FileHelper.parseFile(args[0]); //initialize the list

        // initialize the maps for methods 1, 2, and 3.
        SimpleIndex mysimpleindex = new SimpleIndex();
        mysimpleindex.Build(list);
        InvertedIndexHashMap myhashmap = new InvertedIndexHashMap();
        myhashmap.Build(list);
        InvertedIndexTreeMap mytreemap = new InvertedIndexTreeMap();
        mytreemap.Build(list);


        // read inputs from user
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext())
        {

            // reads each entry and stops if the entry is "quit"
            String line = sc.nextLine();
            if (line.equals("quit")) {
                System.out.println("program exited successfully");
                return;
            }

            if (!isInputValid(line)) {
                System.out.println("Please specify a valid method between 1 and 3");
                continue;
            }

            int method = Integer.parseInt(line.substring(0,1));
            String word = line.substring(2);

            long startTime = System.nanoTime();
            switch (method) { 
                case 1:
                    if (mysimpleindex.lookup(word) == null) {
                        System.out.println("No website contains the query word.");
                    }
                    else {
                        for (Website w : mysimpleindex.lookup(word)) {
                            System.out.println("Word found on " + w.getUrl());
                        }
                    }
                    break;

                case 2:
                    if (mytreemap.lookup(word) == null) {
                        System.out.println("No website contains the query word.");
                    }

                    else {
                        for (Website w : mytreemap.lookup(word)) {
                            System.out.println("Word found on " + w.getUrl());
                        }
                    }
                    break;

                case 3:
                    if (myhashmap.lookup(word) == null) {
                        System.out.println("No website contains the query word.");
                    }
                    else {
                        for (Website w : myhashmap.lookup(word)) {
                            System.out.println("Word found on " + w.getUrl());
                        }
                    }
                    break;
            }
            long endTime = System.nanoTime();
            System.out.println("Total execution time: " + (endTime - startTime)/1000 + " microseconds");
        }
    }

    /**
     * This method tests if the input has length greater or equal to 2
     * and if it starts with 1, 2 or 3.
     *
     * @param input the string to be tested
     * @return true if the input is valid
     *
     * @author Lucas Beck
     */
    public static boolean isInputValid(String input)
    {
        if (input.length() < 2) return false;
        if (!input.substring(0,1).equals("1") && !input.substring(0,1).equals("2") && !input.substring(0,1).equals("3")) return false;
        return true;
    }
}