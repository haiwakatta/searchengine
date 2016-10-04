import java.util.List;

public interface Index {

     void Build(List<Website> websites);
        //prepocesses list of URLs

    List<Website> lookup(String word);
    //returns list of processed websites

}

