package searchengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class takes creates the object QueryEngine, which is responsible
 * for handling complex queries
 */
public class QueryEngine {

    private Index index;

    /**
     *  The constructor of the QueryEngine. Takes an index as parameter
     *  in order to perform the searches in individual words
     * @param index the index as a Map, with keys as words and values as websites
     */
    public QueryEngine(Index index) {
        this.index = index;
    }

    /**
     * This method returns a list of Websites to the query entered.
     * It accepts queries using the 'OR' operator as well as the
     * ' ' operator representing the AND logic. Example "USA OR Canada Brazil"
     * will return the websites that contain either USA or Canada and Brazil.
     * @param query
     * @return list of websites for the specified query
     */
    public List<Website> getWebsites(String query) {
        List<String> queries;
        List<Website> result = new ArrayList<>();

        queries = Arrays.asList(query.split(" OR ")); // splits queries that use OR

        for (String q : queries) {  // iterate through all queries splitted
            for (Website website : subQuery(q)) { // iterate through all websites returned within the subquery method
                if (!result.contains(website)) { // if the result does not contain the website, add it.
                    result.add(website);
                }
            }
        }
        return result;
    }

    /**
     * This method takes a complex query that can contain
     * spaces as separators representing an 'AND' logic.
     * Example: 'USA Brazil' will return the websites containing both USA and Brazil
     * @param query
     * @return list of websites for the specified query
     */
    private List<Website> subQuery (String query){
        List<String> subQueries;
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
