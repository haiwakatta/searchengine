package searchengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by haoqwu on 04/10/2016.
 *
 * @author Stefan Wachmann
 * @author Lucas Beck
 */
public class SimpleIndex implements Index {
    private List<Website> list;
    private int numWebsites;
    private double averageWords;

    /**
     * The build method creates a list of websites from a provided list of websites
     *
     * @param website takes a list of websites as the parameter
     */
    public void build(List<Website> website){
        list = website;
        calculateAverageWords();
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
        numWebsites = ListOfWebsites.size();
        return ListOfWebsites;
    }

    /**
     * This method returns the number of websites contained in the index.
     * @return number of websites.
     */
    @Override
    public int numWebsites() {
        return numWebsites;
    }

    /**
     * This method returns the average number of words per website in the index.
     * @return average number of words per website.
     */
    @Override
    public double averageWords() {
        return averageWords;
    }

    /**
     * This method calculates the average words in the index and assigns it to the instance variable "averageWords"
     */
    private void calculateAverageWords() {
        double numWebsites = 0;
        double numWords = 0;

        for (Website w : list){ // iterate through websites in the index
            numWords += w.getWords().size(); // increases the number of words by the current website list of words size
            numWebsites++;
        }

        averageWords = (numWords/numWebsites);
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

    public List<String> getStarWords(String subQuery) {
        List<String> result = new ArrayList<>();
        for (Website w :list) {
            for (String s : w.getWords()) { // walk through all words on all websites.
                if(s.startsWith(subQuery)) { // if a word starts with the prefix sub-query
                    result.add(s); // then add it to the result list.
                }
            }
        }
        return result; // return a list of words that start with the prefix sub-query.
    }
}