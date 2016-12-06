package searchengine;

import java.util.List;

/**
 * {@code Index} is a interface that represents a objects that contain a group of {@code Website}. The idea is
 *  that this websites can be retrieved by searches made with individual words.
 *
 * @author Stefan Wachmann
 * @author Lucas Beck
 */
public interface Index {

    /**
     * This method takes in a list of {@code Website} objects and create a index
     *
     * @param websites - a list of {@code Website} objects
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
}