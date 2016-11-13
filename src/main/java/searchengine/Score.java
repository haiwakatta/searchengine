package searchengine;

/**
 * Created by user on 13/11/2016.
 */
public interface Score {
    Integer getScore (String word, Website site, Index index);
}
