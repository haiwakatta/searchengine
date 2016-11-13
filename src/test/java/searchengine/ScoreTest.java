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
    private Website website1;
    private Website website2;

    @Before
    public void setUp() {
        List<String> words1 = Arrays.asList("this", "website", "is", "the", "first", "website");
        List<String> words2 = Arrays.asList("this", "website", "is", "the", "second", "website", "it", "is", "cooler");

        website1 = new Website("http://test.com/first", "first", words1);
        website2 = new Website("http://test.com/second", "second", words2);

        index = new InvertedIndex(new HashMap());
        index.build(Arrays.asList(website1,website2));

        scoreTf = new ScoreTf();
    }

    @After
    public void tearDown(){
        index = null;
    }

    @Test
    public void testScoreTf () {
        float score = scoreTf.getScore("website", website1, index);
        Assert.assertEquals("Word website", 2, score, 0.0001);
        score = scoreTf.getScore("banana", website1, index);
        Assert.assertEquals("Word does not exist", 0, score, 0.0001);
    }

}
