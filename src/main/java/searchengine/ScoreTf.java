package searchengine;

/**
 * This class implements the score. It calculates the score according to
 * the number of times a word occur in a particular website.
 */
public class ScoreTf implements Score {

    @Override
    public float getScore(String word, Website site, Index index) {
        float result = 0;
        for (String w : site.getWords()){ // iterate through all words in the website
            if (w.equals(word)) { // if encounter the word, increase score
                result ++;
            }
        }
        return result;
    }
}
