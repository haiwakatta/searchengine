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
                        websitesPerWord.add(currentWebsite); // add current website to the list
                        map.put(currentWord, websitesPerWord);
                    }

                    else { // else, add the current website to the list


                    }
                }
            }


    }

    public List<Website> lookup(String word){
        return null;
    }
}

