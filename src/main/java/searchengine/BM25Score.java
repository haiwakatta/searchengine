package searchengine;

/**
 * This class implements the score. It calculates the score based on a series of
 * parameters. It takes into consideration the words on the website, the average
 * number of words over all websites and some empiric parameters (k,b) that may
 * be varied when tunning for a better result.
 * @author Lucas Beck
 */
public class BM25Score implements Score {

    Score idfScore;
    Score tfScore;
    double k = 1.75;
    double b = 0.75;
    double averageWords;
    private Index index;


    @Override
    public double getScore(String word, Website site, Index index) {
        idfScore = new IDFScore();
        tfScore = new TFScore();

        double denominator;
        double numerator;
        double tf = tfScore.getScore(word,site,index);
        double idf = idfScore.getScore(word,site,index);

        if (!index.equals(this.index)){ // only calculate the average n of words again if the index is different
            this.index = index; // store the index
            averageWords = calculateAverageWords();
        }

        numerator = k +1;
        denominator = (k * ((1 - b) + (b * (tf/averageWords))) + tf);


        return (idf *(tf * (numerator/denominator)));
    }

    private double calculateAverageWords (){

        double numWebsites = 0;
        double numWords = 0;

        for (Website w : index.getWebsite()){
            numWords += w.getWords().size();
            numWebsites++;
        }

        return (numWords/numWebsites);
    }
}
