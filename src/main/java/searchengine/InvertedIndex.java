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
    private List<Website> websites; // store websites so don't have to iterate through all the map again when passing the websites

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
     * this word appears. No website is duplicated for a particular word, even if the word is contained
     * more than once in a website.
     *
     *
     * @param listOfWebsites a preprocessed list of websites with url, title and list of words
     */
    public void build(List<Website> listOfWebsites){
        map.clear();
        numWebsites = listOfWebsites.size();
        websites = listOfWebsites;

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

    @Override
    public List<Website> getWebsites() {
        return websites;
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

    /**
     * This method is used to compare if the current object is equal to a certain object
     * @param o the object to be compared
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvertedIndex that = (InvertedIndex) o;

        if (numWebsites != that.numWebsites) return false;
        if (map != null ? !map.equals(that.map) : that.map != null) return false;
        return websites != null ? websites.equals(that.websites) : that.websites == null;

    }

}
