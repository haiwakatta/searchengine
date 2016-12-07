package searchengine;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class takes creates the object QueryEngine, which is responsible
 * for handling complex queries
 */
public class QueryEngine {

    private Index index;
    private Score score;
    private List<String> prefixStrings = new ArrayList<>();

    /**
     *  The constructor of the QueryEngine. Takes an index as parameter
     *  in order to perform the searches in individual words
     * @param index the index as a Map, with keys as words and values as websites
     */
    public QueryEngine (Index index, Score score) {
        this.index = index;
        this.score = score;
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
        List<String> lowerCaseQueries = new ArrayList<>();
        Map<Website, Double> result = new HashMap<>();
        List<String> starQueries = new ArrayList<>();
        List<String> queriesToRemove = new ArrayList<>();

        queries = Arrays.asList(query.split(" OR ")); // splits queries that use OR

        // makes sure there is no distinction between lower and upper case
        for (String q : queries){
            lowerCaseQueries.add(q.toLowerCase());
        }

        // check if any query is is a prefix search (star query) and find all prefix search queries.
        for (String q : lowerCaseQueries) {
            if (q.contains("*")) {
                queriesToRemove.add(q); // add the current query to a remove list as to remove the queries containing "*" from lowerCaseQueries when not iterating through the list.
                starQueries(q);
                starQueries.addAll(getPrefixStrings()); // add all new queries from the prefix search to a placeholder list.
            }
        }
        lowerCaseQueries.removeAll(queriesToRemove); // remove all queries containing stars.
        lowerCaseQueries.addAll(starQueries); // append all query strings from the placeholder list.

        for (String q : lowerCaseQueries) {  // iterate through all queries split
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
    private Map<Website, Double>  subQuery (String query){
        List<String> subQueries;
        Map<Website, Double> scoredWebsites = new HashMap();
        Map<Website, Double> tempMap = new HashMap();

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
     * This method takes in a query string containing a "*" and matches words in the index that start with the string
     * before the "*" character and returns new queries from the matched words.
     * @param starQuery a query containing the "*" character.
     * @return a list of new queries based on the passed query containing a "*",
     * where any word ending with a "*" is replaced with a word starting with the string before the "*".
     */

    public void starQueries(String starQuery) {
        List<String> starSubQueries = Arrays.asList(starQuery.split(" ")); // splits starQuery into sub-queries.
        List<String> result = new ArrayList<>();
        List<String> tmpList = new ArrayList<>();

        for(String subQ : starSubQueries) { // iterate through the sub-queries in the query string containing a "*".
            if (subQ.endsWith("*")) { // find any word that ends with a "*".
                String trimmedSubQ = subQ.substring(0, subQ.length() - 1); // Adding a trimmed sub-query without the "*" from the sub-query star word.
                for (String s : index.getStarWords(trimmedSubQ)) {
                    if (starQuery.startsWith(subQ)) {
                        tmpList.add(starQuery.replace(subQ, s));
                    } else {
                        tmpList.add(starQuery.replace(" " + subQ, " " + s));
                    }
                }
            }
            result.addAll(tmpList);

        }
        for (String s : result) {
            if (s.contains("*")) {
                starQueries(s);
            }
        }

        if (!result.isEmpty()) {
            for (String r : result) {
                if (!r.contains("*") && !r.equals(null) && r.length() >= starQuery.length()) {
                    setPrefixStrings(r);
                }
            }
        }
        result.clear(); tmpList.clear();
    }

    public void setPrefixStrings(String prefixString) {
        if (!prefixStrings.contains(prefixString)) {
            prefixStrings.add(prefixString);
        }
    }

    public List<String> getPrefixStrings() {
        List<String> getPrefix = new ArrayList<>(prefixStrings);
        prefixStrings.clear();
        return getPrefix;
    }


    /**
     * This function is used to merge two maps into their intersection summing the scores
     * associated with each overlaping entry.
     * @param map1 First map to be merged
     * @param map2 Second map to be merged
     * @return a merged map with websites only common to both and summed scores
     */
    private static Map<Website, Double> intersectionMap (Map<Website, Double> map1, Map<Website, Double> map2){
        Map<Website, Double> result = new HashMap<>();

        for (Website w: map1.keySet()){ // iterate through first map
            if(map2.containsKey(w)){ // if there is an overlapping result
                result.put(w, map1.get(w) + map2.get(w));// sum scores
            }
        }

        return result;
    }
}