package searchengine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 13/11/2016.
 */
public class ScoreTest {
    private Index index;
    private Score scoreTf;
    private Score scoreIdf;
    private Score scoreTfidf;
    private Website website1;
    private Website website2;
    private Website website3;

    @Before
    public void setUp() {
        List<String> words1 = Arrays.asList("this", "website", "is", "the", "first", "website");
        List<String> words2 = Arrays.asList("this", "website", "is", "the", "second", "website", "it", "is", "cooler");
        List<String> words3 = Arrays.asList("this", "website", "is", "the", "third", "website", "it", "is", "even", "cooler",
                " it", "is", "twice", "cooler");

        website1 = new Website("http://test.com/first", "first", words1);
        website2 = new Website("http://test.com/second", "second", words2);
        website3 = new Website("http://test.com/third", "third", words3);

        index = new InvertedIndex(new HashMap());
        index.build(Arrays.asList(website1,website2, website3));

        scoreTf = new TFScore();
        scoreIdf = new IDFScore();
        scoreTfidf = new TFIDFScore();
    }

    @After
    public void tearDown(){
        index = null;
        scoreTf = null;
        scoreIdf = null;
    }

    @Test
    public void testScoreTf () {
        // word that occur twice in the website
        double score = scoreTf.getScore("website", website1, index);
        Assert.assertEquals("Word website", 2, score, 0.01);

        // word that does not occur at all in the website
        score = scoreTf.getScore("banana", website1, index);
        Assert.assertEquals("Word does not exist", 0, score, 0.01);

        // word that occur once in the website
        score = scoreTf.getScore("cooler", website2, index);
        Assert.assertEquals("Word cooler", 1, score, 0.01);
    }

    @Test
    public void testScoreIdf (){
        // word that occur in one website
        double score = scoreIdf.getScore("second", website2, index);
        Assert.assertEquals("Word second", 1.58, score, 0.01);

        // word that does not occur
        score = scoreIdf.getScore("banana", website2, index);
        Assert.assertEquals("Word does not exist", 0, score, 0.01);

        // word that occur in two websites
        score = scoreIdf.getScore("cooler", website2, index);
        Assert.assertEquals("Word cooler", 0.58, score, 0.01);
    }

    @Test
    public void testScoreTfidf () {
        // word that occur in one website
        double score = scoreTfidf.getScore("second", website2, index);
        Assert.assertEquals("Word second", 1.58, score, 0.01);

        // word that does not occur
        score = scoreTfidf.getScore("banana", website2, index);
        Assert.assertEquals("Word does not exist", 0, score, 0.01);

        // word that occur in two websites
        score = scoreTfidf.getScore("cooler", website3, index);
        Assert.assertEquals("Word cooler", 1.16, score, 0.01);
    }

}
