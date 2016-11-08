package searchengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 01/11/2016.
 */
public class QueryEngine {

    private String query;
    private Index index;
    private List<Website> listWebsite = null;

    public QueryEngine(Index index) {
        this.index = index;
    }

    public List<Website> getWebsites(String query) {
        List<String> queries = new ArrayList<String>();
        List<Website> result = new ArrayList<>();

        queries = Arrays.asList(query.split(" OR "));

        for (String q : queries) {
            for (Website website : subQuery(q)) {
                if (!result.contains(website)) {
                    result.add(website);
                }
            }
        }
        return result;
    }

    private List<Website> subQuery (String query){
        List<String> subQueries = new ArrayList<String>();
        List<Website> subResult = new ArrayList<>();

        subQueries = Arrays.asList(query.split(" "));

        for(String subQ : subQueries){
            if (subResult.isEmpty()) {
                subResult = index.lookup(subQ);
            }
            else {
                subResult.retainAll(index.lookup(subQ));
            }
        }

        return subResult;
    }
}
