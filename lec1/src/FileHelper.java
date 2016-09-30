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
        int previousLine = 0; // previous line is going to store 1 for URL, 2 for title and 0 for words

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
                        result.add(new Website(url, title, wordList));
                    }

                    // if reaches new website, sets line and title to null and saves url
                    title = null;
                    wordList = null;
                    url = line.substring(6); // gets a substring from index 6 onwards
                }

                else if (url != null && title == null)  // if it found an url, save the title
                {
                    title = line;
                }

                // add the words to the list
                else if (url != null && title != null)
                {
                    if (wordList == null) // checks if the word for this website was initialized
                    {
                        wordList = new ArrayList<String>();
                    }
                    wordList.add(line);
                }
            }
            if (url != null) {
                result.add(new Website(url, title, wordList));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
