package searchengine;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *  This classe implements the interface index using a TreeMap.
 *  It takes a list of {@code Website} and builts it in a inverted index with a TreeMap.
 *
 *  @author Lucas Beck
 */

public class InvertedIndexTreeMap implements Index {

    private TreeMap<String, List<Website>> map = new TreeMap<String, List<Website>>();

    /**
     * This method takes a list of websites and initialize a Treemap with its inputs
     * where each key is a word and each value is a list of the websites in which
     * this word appears. Words that appear more than once in a website are counted
     * only once.
     *
     * @param listofWebsites a preprocessed list of websites with url, title and list of words
     * @author Lucas Beck
     */
    public void Build(List<Website> listofWebsites) {

        for (Website currentWebsite : listofWebsites) { // iterate through the list of websites

            for (String currentWord : currentWebsite.getWords()) {// iterate through the list of words of each website

                if (!map.containsKey(currentWord)) { // if word has no websites associate with it yet, create a list of websites

                    List<Website> websitesPerWord = new ArrayList<Website>(); // create list of websites
                    map.put(currentWord, websitesPerWord);
                }

                if (!isWebsiteContained(map.get(currentWord), currentWebsite)) { // check if the current website is already in the list
                    map.get(currentWord).add(currentWebsite); // add the currentwebsite to the list for the currentword

                }
            }
        }
    }

    /**
     * A static method used to test if a website is already contained
     * in a list of websites.
     *
     * @param list    list of website's
     * @param website {@code Website} the website to be searched
     * @return true if the list contains the website.
     * @author Lucas Beck
     */

    public static boolean isWebsiteContained(List<Website> list, Website website) {
        for (Website w : list) {
            if (w.equals(website)) return true;
        }
        return false;
    }

    /**
     * This method provides the list of websites according to the word entered
     * as a parameter.
     *
     * @param word the word which to search for websites
     * @return a list of websites that contains the word entered.
     */

    public List<Website> lookup(String word) {

        return map.get(word);
    }

    //Test Methods
    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public boolean containsValue(String value) {
        return map.containsKey(value);
    }

    public int size() {
        return map.size();
    }
}