package searchengine;

import java.util.List;

/**
 * {@code Index} refers to an object which contains a number of {@code Website} and some methods
 * to test wether words are contained in that list
 *
 * @author Stefan Wachmann
 * @author Lucas Beck
 */
public interface Index {

    /**
     * This method takes and preprocessed a list of website objects and create an index
     *
     * @param websites - the list of websites to be preprocessed.
     *
     * @author Stefan Wachmann
     * @author Lucas Beck
     */
    void build(List<Website> websites);

    /**
     * This method takes a query string and returns a list of websites where the query is contained.
     *
     * @param query - the query string to search for     *
     * @return the list of processed websites.
     *
     * @author Stefan Wachmann
     * @author Lucas Beck
     */
    List<Website> lookup(String query);
}