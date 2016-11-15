package searchengine;

/**
 * This class implements the score. It calculates the score as a product
 * of the Idf implementation and the Tf implementation
 */
public class TFIDFScore implements Score {
    @Override
    public double getScore(String word, Website site, Index index) {

        Score tf = new TFScore();
        Score idf = new IDFScore();

        double tfScore = tf.getScore(word, site, index);
        double idfScore = idf.getScore(word, site, index);

        return tfScore * idfScore;
    }
}
