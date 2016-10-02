import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by maau on 27/09/16.
 */
public class FileHelper {
    public static List<Website> parseFile(String arg) {

        List<Website> result = new ArrayList<Website>();
        String url = null;
        String title = null;
        List<String> wordList = null;
        boolean previousWord = true; // check if the previous line was a word

        // perform iterations in order to create the list of websites to be returned
        try
        { // try to read file, otherwise go to catch
            Scanner sc = new Scanner(new File(arg));
            while (sc.hasNext()) {
                String line = sc.next().trim();
                if (line.startsWith("*PAGE:"))
                {
                    // new entry starts
                    // save the old one
                    if (url != null)
                    {
                        if (previousWord == true)  // only add the entry if the last line read was a word
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
            if (url != null && previousWord == true) {
                result.add(new Website(url, title, wordList)); // only add the entry if the last line read was a word
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
