package searchengine;

/**
 * Created by user on 13/11/2016.
 */
public class ScoreTf implements Score {

    @Override
    public Integer getScore(String word, Website site, Index index) {
        int result = 0;
        for (String w : site.getWords()){
            if (w.equals(word)) {
                result ++;
            }
        }
        return result;
    }
}
