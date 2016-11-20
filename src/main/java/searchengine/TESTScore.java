package searchengine;

/**
 * This implementation of score is only used to test the query engine functionality.
 * By having this implementation we guarantee that if the test fails is not because the scores are not working
 * but rather the query engine itself.
 * @author Lucas Beck
 */
public class TESTScore implements Score {

    /**
     * The method return the score as the length of the word
     * @param word word to be assigned a score
     * @param site a website the word is contained
     * @param index the index of the database of websites
     * @return
     */
    @Override
    public double getScore(String word, Website site, Index index) {
        return word.length();
    }
}
