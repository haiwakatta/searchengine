package searchengine;

import java.util.*;

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


    private Index index;
    private List<Website> listWebsite = new ArrayList<Website>();

    public QueryEngine (Index Index) {
        this.index = Index;
    }



    public List<Website> getWebsites(String query){
        List<String> queries = new ArrayList<>();

         /*
        if (query.contains(" ")) {
            queries = Arrays.asList(query.split(" "));
        }

        for (String q : queries) {

        }
        */

        if (query.contains(" ")){
            queries = Arrays.asList(query.split(" OR "));}
        else queries.add(query);

        for (int i = 0; i < queries.size(); i++) {
            //Check if query is AND operator
            if (queries.get(i).contains(" AND ") || queries.get(i).contains(" ")) {
                //Create a new list containing each term in an AND query
                String[] andQueries = queries.get(i).split(" ");
                //Initialize and resets new website container
                List<Website> andList = new ArrayList<Website>();
                //Loops through terms in an AND query and adds them to container
                for (int j = 0; j < andQueries.length; j++) {
                    andList.addAll(index.lookup(andQueries[j]));
                }
                //add only duplicate items to final list
                listWebsite.addAll(returnDuplicates(andList));
            }
            System.out.println(queries.get(i));
            listWebsite.addAll(index.lookup(queries.get(i)));
        }
        return listWebsite;
    }


}



