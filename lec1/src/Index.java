import java.util.List;

/** The index creates a list of preprocessed website objects from a list of websites and a search query.
 *
 * @author Stefan
 */
public interface Index {

    /** This method takes and preprocesses a list of website objects.
     *
     * @param websites - the list of websites to be preprocessed.
     */
    void Build(List<Website> websites);

    /** This method takes a query string and returns a list of websites where the query is contained.
     *
     * @param query - the query string to search for.
     *
     * @return the list of processed websites.
     */
    List<Website> lookup(String query);
}