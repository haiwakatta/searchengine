import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class InvertedIndexTreeMap implements Index {

    private TreeMap<String, List<Website>> map = new TreeMap<String, List<Website>>();

    public void Build(List<Website> listofWebsites){

            for (Website currentWebsite: listofWebsites ){ // iterate through the list of websites

                for (String currentWord: currentWebsite.getWords()) {// iterate through the list of words of each website

                    if (!map.containsKey(currentWord)){ // if word has no websites associate with it yet, create a list of websites

                        List<Website> websitesPerWord = new ArrayList<Website>(); // create list of websites
                        map.put(currentWord, websitesPerWord);
                    }
                        map.get(currentWord).add(currentWebsite); // add the currentwebsite to the list for the currentword

                    }
                }
            }




    public List<Website> lookup(String word){

        return map.get(word);
    }
}

