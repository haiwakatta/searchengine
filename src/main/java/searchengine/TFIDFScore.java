package searchengine;

/**
 * This class implements the score. It calculates the score as a product
 * of the Idf implementation and the Tf implementation. Thus, the score is
 * relative not only to to the number of time the word appears in the
 * website, but also in how many websites the word appear relative to the database size.
 * @author Lucas Beck
 */
public class TFIDFScore implements Score {

    /**
     * This method returns the score of the word using the TFIDF calculation systematic
     * @param word word to be assigned a score
     * @param site a website the word is contained
     * @param index the index of the database of websites
     * @return the score of the word on a particular website given an index
     */
    @Override
    public double getScore(String word, Website site, Index index) {

        Score tf = new TFScore();
        Score idf = new IDFScore();

        double tfScore = tf.getScore(word, site, index);
        double idfScore = idf.getScore(word, site, index);

        return tfScore * idfScore;
    }
}
