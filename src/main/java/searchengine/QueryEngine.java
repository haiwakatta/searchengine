package searchengine;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 01/11/2016.
 */
public class QueryEngine {

    //Private class that deduplicates websites a list
    private <T> Set<T> returnDuplicates(Collection<T> list) {

        Set<T> duplicates = new HashSet<T>();
        Set<T> uniques = new HashSet<T>();

        for(T t : list) {
            if(!uniques.add(t)) {
                duplicates.add(t);
            }
        }
        return duplicates;
    }


    private String query;
    private InvertedIndex index;
    private List<Website> listWebsite = null;

    public QueryEngine (InvertedIndex Index) {
        this.index = Index;
    }

    public List<Website> getWebsites(String query){
        String[] queries = query.split(" OR ");

        for (int i = 0; i < queries.length; i++) {
            //Check if query is AND operator
            if (queries[i].contains(" AND ") || queries[i].contains(" ")) {
                //Create a new list containing each term in an AND query
                String[] andQueries = queries[i].split(" ");
                //Initialize and resets new website container
                List<Website> andList = null;
                //Loops through terms in an AND query and adds them to container
                for (int j = 0; j < andQueries.length; j++) {
                    andList.addAll(index.lookup(andQueries[j]));
                }
                //add only duplicate items to final list
                listWebsite.addAll(returnDuplicates(andList));
            }

                listWebsite.addAll(index.lookup(queries[i]));
        }
        return listWebsite;
    }
}



