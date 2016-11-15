package searchengine;

/**
 * Created by user on 15/11/2016.
 */
public class SimpleScore implements Score {
    @Override
    public double getScore(String word, Website site, Index index) {
        return 0;
    }
}
