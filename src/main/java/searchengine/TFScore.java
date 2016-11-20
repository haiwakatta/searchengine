package searchengine;

/**
 * This class implements the score. It calculates the score according to
 * the number of times a word occur in a particular website.
 * @author Lucas Beck
 */
public class TFScore implements Score {

    /**
     * This method returns the score of the word using the TF calculation systematic.
     * @param word word to be assigned a score
     * @param site a website the word is contained
     * @param index the index of the database of websites
     * @return the score of the word on a particular website given an index
     */
    @Override
    public double getScore(String word, Website site, Index index) {
        double result = 0;
        for (String w : site.getWords()){ // iterate through all words in the website
            if (w.equals(word)) { // if encounter the word, increase score
                result ++;
            }
        }
        return result;
    }
}
