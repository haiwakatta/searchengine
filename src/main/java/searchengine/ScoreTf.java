package searchengine;

/**
 * Created by user on 13/11/2016.
 */
public class ScoreTf implements Score {

    @Override
    public float getScore(String word, Website site, Index index) {
        float result = 0;
        for (String w : site.getWords()){
            if (w.equals(word)) {
                result ++;
            }
        }
        return result;
    }
}
