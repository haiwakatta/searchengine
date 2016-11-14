package searchengine;

/**
 * This class implements the score. It calculates the score according to
 * the total number of websites in the Index (database) relative to the number of websites a word occur
 */
public class ScoreIdf implements Score {
    @Override
    public float getScore(String word, Website site, Index index) {
        float totalNum = 0;
        float siteNum = 0;

        siteNum = index.lookup(word).size(); // total number of websites that the word occur
        totalNum = index.numWebsites(); // total number of websites in the data base

        if (siteNum == 0){ // if the word is not cointained at all, avoid division by 0
            return 0;
        }
        return logBase2((totalNum/siteNum));
    }

    /**
     * This function returns the log base 2 given a float
     * @param x float number
     * @return the log base 2 in a float number
     */
    public static float logBase2 (float x){
        return (float) (Math.log(x)/Math.log(2));
    }
}
