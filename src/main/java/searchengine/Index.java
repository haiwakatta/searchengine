package searchengine;

import java.util.List;

/**
 * {@code Index} refers to an object which contains a number of {@code Website} and some methods
 * to test whether words are contained in that list
 *
 * @author Stefan Wachmann
 * @author Lucas Beck
 */
public interface Index {

    /**
     * This method takes and preprocessed a list of website objects and create an index
     *
     * @param websites - the list of websites to be preprocessed.
     */
    void build(List<Website> websites);

    /**
     * This method takes a query string and returns a list of websites where the query is contained.
     *
     * @param query - the query string to search for
     * @return the list of processed websites.
     *
     */
    List<Website> lookup(String query);

    /**
     * This method returns the total number of websites within the Index
     * @return the number of websites
     */
    int numWebsites();

    /**
     * This method returns the average number of words per website in the index.
     * @return average number of words per website.
     */
    double averageWords ();

    /**
     * This method returns a list of words that starts with the subQ string and is contained in any of the websites.
     * @param subQ - a prefix search query string.
     * @return List of words that start with the subQ string.
     */
    List<String> getStarWords(String subQ);
}