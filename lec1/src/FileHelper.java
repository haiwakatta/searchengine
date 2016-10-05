import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class takes care of reading an input database file and
 * transforming it into {@code Website} objects.
 *
 * @author Martin Aumüller
 */

public class FileHelper {

    /**
     * This methods transforms a database file into a list of {@code Website}
     * objects. The list of words has the same order as the entries
     * in the database file.
     *
     * @param arg the filename of the database
     * @return the list of websites that are contained in the database file.
     */
    public static List<Website> parseFile(String arg) {

        List<Website> result = new ArrayList<Website>();
        String url = null;
        String title = null;
        List<String> wordList = null;
        boolean previousWord = true; // check if the previous line was a word

        // perform iterations in order to create the list of websites to be returned
        try
        { // try to read file, otherwise go to catch
            Scanner sc = new Scanner(new File(arg), "UTF-8");
            while (sc.hasNext()) {
                String line = sc.next().trim();
                if (line.startsWith("*PAGE:"))
                {
                    // new entry starts
                    // save the old one
                    if (url != null)
                    {
                        if (previousWord)  // only add the entry if the last line read was a word
                        {
                            result.add(new Website(url, title, wordList));
                        }
                    }

                    // if reaches new website, sets line and title to null and saves url
                    title = null;
                    wordList = null;
                    url = line.substring(6); // gets a substring from index 6 onwards
                    previousWord = false;
                }

                else if (url != null && title == null)  // if it found an url, save the title
                {
                    title = line;
                    previousWord = false;
                }

                // add the words to the list
                else if (url != null && title != null)
                {
                    if (wordList == null) // checks if the word for this website was initialized
                    {
                        wordList = new ArrayList<String>();
                        previousWord = true;
                    }
                    wordList.add(line);
                }
            }

            // prints the last entry
            if (url != null && previousWord) {
                result.add(new Website(url, title, wordList)); // only add the entry if the last line read was a word
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
