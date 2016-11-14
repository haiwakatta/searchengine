package searchengine;

import java.util.*;
import java.util.stream.Collectors;

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
    public QueryEngine (Index index) {
        this.index = index;
    }

    /**
     * This method returns a list of Websites to the query entered.
     * It accepts queries using the 'OR' operator as well as the
     * ' ' operator representing the AND logic. Example "USA OR Canada Brazil"
     * will return the websites that contain either USA or Canada and Brazil.
     * It also returns the websites in order of relevance
     * @param query the query to be searched
     * @return list of websites for the specified query
     */
    public List<Website> getWebsites(String query) {
        List<String> queries;
        Map<Website, Float> result = new HashMap<>();

        queries = Arrays.asList(query.split(" OR ")); // splits queries that use OR

        for (String q : queries) {  // iterate through all queries split
            for (Website w: subQuery(q).keySet()){ // send the queries split by "or" to be processed by subquery and then iterate in the websites returned
                if (!result.containsKey(w)){ // if the result was not added yet
                    result.put(w, subQuery(q).get(w)); // put the result with its score
                }
                else {
                    if (result.get(w) < subQuery(q).get(w)){ // if the result is contained but score is less, then substitute score
                        result.put(w, subQuery(q).get(w));
                    }
                }
            }
        }
        // returns the list in score order
        return result. entrySet () . stream () . sorted (( x , y ) -> y . getValue () .
                compareTo ( x . getValue () ) ) . map ( Map . Entry :: getKey ) . collect (
                Collectors. toList () );
    }

    /**
     * This method takes a complex query that can contain
     * spaces as separators representing an 'AND' logic.
     * Example: 'USA Brazil' will return the websites containing both USA and Brazil.
     * Each website has a score associated with it according to the relevance.
     * @param query the query to be searched
     * @return Map containing websites and its scores.
     */
    private Map<Website, Float>  subQuery (String query){
        List<String> subQueries;
        Map<Website, Float> scoredWebsites = new HashMap();
        Map<Website, Float> tempMap = new HashMap();
        Score score = new ScoreTf();

        subQueries = Arrays.asList(query.split(" ")); // splits query

        for(String subQ : subQueries){ // iterate through every split query
            if (scoredWebsites.isEmpty()) { // if the map is still empty
                for (Website w : index.lookup(subQ) ){ // iterate through the websites found for the word
                    scoredWebsites.put(w,score.getScore(subQ,w,this.index)); //populate the map the first time with websites and score
                }
            }
            else { // uses a temporary map to compare the existing websites with the ones from the next query
                for (Website w : index.lookup(subQ) ){ // iterate through the websites found for the word
                    tempMap.put(w,score.getScore(subQ,w,this.index)); // populate temporary map
                }
                scoredWebsites = intersectionMap(scoredWebsites, tempMap); // merge maps
                tempMap.clear(); // make sure to clear results to be compared
            }
        }

        return scoredWebsites;
    }


    /**
     * This function is used to merge two maps into their intersection summing the scores
     * associated with each overlaping entry.
     * @param map1 First map to be merged
     * @param map2 Second map to be merged
     * @return a merged map with websites only common to both and summed scores
     */
    private static Map<Website, Float> intersectionMap (Map<Website, Float> map1, Map<Website, Float> map2){
        Map<Website, Float> result = new HashMap<>();

        for (Website w: map1.keySet()){ // iterate through first map
            if(map2.containsKey(w)){ // if there is an overlapping result
                result.put(w, map1.get(w) + map2.get(w));// sum scores
            }
        }

        return result;
    }
}
