package searchengine;

/**
 * Created by user on 15/11/2016.
 */
public class ScoreTfidf implements Score {
    @Override
    public double getScore(String word, Website site, Index index) {

        Score tf = new ScoreTf();
        Score idf = new ScoreIdf();

        double tfScore = tf.getScore(word, site, index);
        double idfScore = idf.getScore(word, site, index);

        return tfScore * idfScore;
    }
}
