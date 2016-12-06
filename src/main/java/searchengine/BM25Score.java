package searchengine;

/**
 * This class implements the score. It calculates the score based on a series of
 * parameters. It takes into consideration the words on the website, the average
 * number of words over all websites and some empiric parameters (k,b) that may
 * be varied when tunning for a better result.
 * @author Lucas Beck
 */
public class BM25Score implements Score {

    private Score idfScore;
    private Score tfScore;
    private double k;
    private double b;
    private double averageWords;

    /**
     * This method constructs the BM25Score object.
     */
    public BM25Score() {
        idfScore = new IDFScore();
        tfScore = new TFScore();
        k = 1.75;
        b = 0.75;
    }


    /**
     * This method calculates the BM25Score given a word, a website and a database (index).
     * See the systematic here: https://en.wikipedia.org/wiki/Okapi_BM25
     *
     * @param word word to be assigned a score
     * @param site a website the word is contained
     * @param index the index of the database of websites
     * @return
     */
    @Override
    public double getScore(String word, Website site, Index index) {

        double numberOfWords = site.getWords().size();
        double tf = tfScore.getScore(word,site,index);
        double idf = idfScore.getScore(word,site,index);

        averageWords = index.averageWords();

        double numerator = k +1;
        double denominator = (k * ((1 - b) + (b * (numberOfWords/averageWords))) + tf);


        return (idf *(tf * (numerator/denominator)));
    }

    /**
     * This method calculates the average number of words in the index
     * associated with the score class.
     * @return average number of words across websites in the index as a double
     */

    /*
    private double calculateAverageWords (){

        double numWebsites = 0;
        double numWords = 0;

        for (Website w : index.getWebsites()){ // iterate through websites in the index
            numWords += w.getWords().size(); // increases the number of words by the current website list of words size
            numWebsites++;
        }

        return (numWords/numWebsites);
    }
    */
}
