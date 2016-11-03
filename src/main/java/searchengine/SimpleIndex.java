package searchengine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoqwu on 04/10/2016.
 *
 * @author Stefan Wachmann
 * @author Lucas Beck
 */
public class SimpleIndex implements Index {
    public List<Website> list;

    /**
     * The build method creates a list of websites from a provided list of websites
     *
     * @param website takes a list of websites as the parameter
     */
    public void build(List<Website> website){
        list = website;
    }

    /**
     * The lookup method takes a word query and determines if it is contained in a given list of websites.
     *
     * @param word takes a search query as the parameter
     * @return list of websites in which the word is contained.
     */
    public List<Website> lookup(String word){
        List<Website> ListOfWebsites = new ArrayList<Website>();
        // Search for line in the list of websites
        for (Website w: list)
        {
            if (w.containsWord(word))
            {
                ListOfWebsites.add(w);
            }
        }
        return ListOfWebsites;
    }

    /**
     * This method returns a String representation of the Index
     *
     * @return SimpleIndex string representation
     */
    @Override
    public String toString() {
        return "SimpleIndex{" +
                "list=" + list +
                '}';
    }

    //Test Methods
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean containsKey(String key) {
        return list.contains(key);
    }

    public int size() {
        return list.size();
    }
}