import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoqwu on 04/10/2016.
 *
 * @author Stefan Wachmann
 */
public class SimpleIndex implements Index {
    public static List<Website> list;

    public void Build(List<Website> website){
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
        return ListOfWebsites;
    }

    //Test Methods
    public static boolean isEmpty() {
        return list.isEmpty();
    }

    public static boolean containsKey(String key) {
        return list.contains(key);
    }

    public static int size() {
        return list.size();
    }
}
