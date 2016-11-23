package searchengine;

import java.util.*;


/**
 *  This class implements the interface index in a inverted manner using a map
 *
 *  @author Lucas Beck
 */

public class InvertedIndex implements Index {

    private Map<String, List<Website>> map;
    private int numWebsites;

    /**
     * The constructor of the InvertedIndex object.
     * takes the type of map to be created
     */
    public InvertedIndex(Map map) {
        this.numWebsites = 0;
        this.map = map;
    }

    /**
     * This method takes a list of websites and initialize the map with its inputs
     * where each key is a word and each value is a list of the websites in which
     * this word appears. Words that appear more than once in a website are counted
     * only once.
     *
     * @param listOfWebsites a preprocessed list of websites with url, title and list of words
     */
    public void build(List<Website> listOfWebsites){
        map.clear();
        numWebsites = listOfWebsites.size();
        for (Website currentWebsite: listOfWebsites ){ // iterate through the list of websites

            for (String currentWord: currentWebsite.getWords()) {// iterate through the list of words of each website

                if (!map.containsKey(currentWord)){ // if word has no websites associate with it yet, create a list of websites

                    List<Website> websitesPerWord = new ArrayList<Website>(); // create list of websites
                    map.put(currentWord, websitesPerWord);
                }

                if (!isWebsiteContained(map.get(currentWord), currentWebsite)) { // check if the current website is already in the list
                    map.get(currentWord).add(currentWebsite); // add the currentWebsite to the list for the currentWord
                }
            }
        }
    }

    /**
     *  This method provides the list of websites according to the word entered
     *  as a parameter.
     *
     * @param word the word which to search for websites
     * @return a list of websites that contains the word entered.
     */
    public List<Website> lookup(String word){

        if (map.get(word) == null){ // returns an empty list and not null in case it does not find any website
            return new ArrayList<Website>();
        }
        return map.get(word);
    }

    @Override
    public int numWebsites() {
        return numWebsites;
    }

    /**
     * A static method used to test if a website is already contained
     * in a list of websites.
     *
     * @param list    list of website's
     * @param website {@code Website} the website to be searched
     * @return true if the list contains the website.
     */

    private static boolean isWebsiteContained(List<Website> list, Website website) {
        for (Website w : list) {
            if (w.equals(website)) return true;
        }
        return false;
    }

    public int size() {
        return map.size();
    }

    /**
     * This method returns a String representation of the Index
     *
     * @return InvertedIndex string representation
     */
    @Override
    public String toString() {
        return "InvertedIndex{" +
                "map=" + map +
                '}';
    }
}
