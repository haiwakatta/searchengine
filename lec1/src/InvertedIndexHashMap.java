import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 *  This classe implements the interface index using a HashMap.
 *  It takes a list of {@code Website} and builts it in a inverted index with a TreeMap.
 *
 *  @author Lucas Beck
 */

public class InvertedIndexHashMap implements Index {
    private Map<String, String> a = new HashMap<String, String>();

    private static HashMap<String, List<Website>> map = new HashMap<String, List<Website>>();


    /**
     * This method takes a list of websites and initialize a HashMap with its inputs
     * where each key is a word and each value is a list of the websites in which
     * this word appears. Words that appear more than once in a website are counted
     * only once.
     *
     * @param listofWebsites a preprocessed list of websites with url, title and list of words
     *
     * @author Lucas Beck
     */
    public void Build(List<Website> listofWebsites){
        for (Website currentWebsite: listofWebsites ){ // iterate through the list of websites

            for (String currentWord: currentWebsite.getWords()) {// iterate through the list of words of each website

                if (!map.containsKey(currentWord)){ // if word has no websites associate with it yet, create a list of websites

                    List<Website> websitesPerWord = new ArrayList<Website>(); // create list of websites
                    map.put(currentWord, websitesPerWord);
                }

                if (!InvertedIndexTreeMap.isWebsiteContained(map.get(currentWord), currentWebsite)) { // check if the current website is already in the list
                    map.get(currentWord).add(currentWebsite); // add the currentwebsite to the list for the currentword

                }
            }
        }


    }

    /**
     *  This method provides the list of websites according to the word entered
     *  as a parameter.
     *
     * @param word the word which to search for websites
     * @return a list of websites that contains the word entered.
     */
    public List<Website> lookup(String word){

        return map.get(word);
    }

    //Test Methods
    public static boolean isEmpty() {
        return map.isEmpty();
    }

    public static boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public static boolean containsValue(String value) {
        return map.containsKey(value);
    }

    public static int size() {
        return map.size();
    }
}
