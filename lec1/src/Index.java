import java.util.List;

/**
 *
 */
public interface Index {

    /**
     * This method preprocesses a list of website objects.
     * @param websites -
     */
    void Build(List<Website> websites);
        //prepocesses list of URLs

    /**
     *
     * @param query
     * @return the list of processed websites.
     */
    List<Website> lookup(String query);
    //returns list of processed websites

}

