package searchengine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoqwu on 04/10/2016.
 *
 * @author Stefan Wachmann
 */
public class SimpleIndex implements Index {
    public List<Website> list;

    public void build(List<Website> website){
        list = website;
    }

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
        if (!ListOfWebsites.isEmpty())return ListOfWebsites;
        else return null;
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
