package searchengine;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class takes creates the object QueryEngine, which is responsible
 * for handling complex queries
 */
public class QueryEngineMap implements Query {

    private Index index;

    /**
     *  The constructor of the QueryEngine. Takes an index as parameter
     *  in order to perform the searches in individual words
     * @param index the index as a Map, with keys as words and values as websites
     */
    public QueryEngineMap (Index index) {
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
        Map<Website, Float> result = new HashMap<>();

        queries = Arrays.asList(query.split(" OR ")); // splits queries that use OR

        for (String q : queries) {  // iterate through all queries splitted
            for (Website w: subQuery(q).keySet()){ // iterate through all websites from the querie
                if (!result.containsKey(w)){ // if the result was not added yet
                    result.put(w, subQuery(q).get(w)); // put the result
                }
                else {
                    if (result.get(w) < subQuery(q).get(w)){ // if the result is contained but score is less, then substitute score
                        result.put(w, subQuery(q).get(w));
                    }
                }
            }
        }
        return result. entrySet () . stream () . sorted (( x , y ) -> y . getValue () .
                compareTo ( x . getValue () ) ) . map ( Map . Entry :: getKey ) . collect (
                Collectors. toList () );
    }

    /**
     * This method takes a complex query that can contain
     * spaces as separators representing an 'AND' logic.
     * Example: 'USA Brazil' will return the websites containing both USA and Brazil
     * @param query
     * @return list of websites for the specified query
     */
    private Map<Website, Float>  subQuery (String query){
        List<String> subQueries;
        Map<Website, Float> scoredWebsites = new HashMap();
        Score score = new ScoreTf();

        subQueries = Arrays.asList(query.split(" "));

        for(String subQ : subQueries){
            if (scoredWebsites.isEmpty()) {
                for (Website w : index.lookup(subQ) ){
                    scoredWebsites.put(w,score.getScore(subQ,w,this.index)); //populate the map the first time
                }
            }
            else { // uses a temporary map to compare the existing websites to the ones from the next query
                Map<Website, Float> tempMap = new HashMap();
                for (Website w : index.lookup(subQ) ){
                    tempMap.put(w,score.getScore(subQ,w,this.index));
                }
                scoredWebsites = intersectionMap(scoredWebsites, tempMap);
                tempMap.clear(); // make sure to clear results to be compared
            }
        }

        return scoredWebsites;
    }


    private Map<Website, Float> intersectionMap (Map<Website, Float> map1, Map<Website, Float> map2){
        Map<Website, Float> result = new HashMap<>();

        for (Website w: map1.keySet()){
            if(map2.containsKey(w)){
                result.put(w, map1.get(w) + map2.get(w));// sum scores
            }
        }

        return result;
    }
}
