package searchengine;

import java.util.List;

/**
 * Created by user on 13/11/2016.
 */
public interface Query {
    public List<Website> getWebsites(String query);
}
