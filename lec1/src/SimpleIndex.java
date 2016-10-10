import java.util.List;

/**
 * Created by haoqwu on 04/10/2016.
 */
public class SimpleIndex implements Index {
    List<Website> SimpleList;
    boolean foundWordInList;

    public void Build(List<Website> website){
        SimpleList = website;
    }

    public List<Website> lookup(String word){
        // Search for line in the list of websites
        for (Website w: SimpleList)
        {
            if (w.containsWord(word))
            {
                System.out.println("Word found on " + w.getUrl());
                foundWordInList = true;
            }
        }
        if (!foundWordInList) System.out.println("No website contains the query word.");
        return null;
    }
}
