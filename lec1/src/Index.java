import java.util.List;

/**
 *
 * @author Stefan
 */
public interface Index {

    /** This method preprocesses a list of website objects.
     *
     * @param websites - the list of websites to be preprocessed.
     */
    void Build(List<Website> websites);
        //prepocesses list of URLs

    /** This method looks up a query string and returns a list of websites where the query is contained.
     *
     * @param query - the query string to search for.
     *
     * @return the list of processed websites.
     */
    List<Website> lookup(String query);
    //returns list of processed websites
}