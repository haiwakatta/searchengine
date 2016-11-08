package searchengine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Stefan on 5/11/16.
 *
 * @author Stefan Wachmann
 * @author Lucas Beck
 */
public class QueryEngineTest {

    private Index index;
    private QueryEngine query;

    @Before
    public void setUp() {
        index = new InvertedIndex(new HashMap());
        List<String> words1 = Arrays.asList("first","website", "for", "testing", "the", "query");
        List<String> words2 = Arrays.asList("second", "website", "for", "testing");
        List<String> words3 = Arrays.asList("third", "website", "for", "testing", "with", "extra", "words");

        Website website1 = new Website("http://example.com/first", "first", words1);
        Website website2 = new Website("http://example.com/second", "second", words2);
        Website website3 = new Website("http://example.com/third", "third", words3);

        index.build(Arrays.asList(website1, website2, website3));
        query = new QueryEngine(index);

        /*longQuery1 = "Where are the absolute best restaurants in Copenhagen";
        longQuery2 = "various of the extremely largescale states are selfgoverning";
        separatorsQuery1 = "commonly AND country OR happiest zealand OR  ";
        separatorsQuery2 = "ituniversitetet OR and AND or OR https";
        emptyQuery = "";*/

    }
    @After
    public void tearDown() {
        index = null;
        query = null;
        /*longQuery1 = null;
        longQuery2 = null;
        separatorsQuery1 = null;
        separatorsQuery2 = null;
        emptyQuery = null;*/

    }

    @Test
    public void queryTest() {
        List<Website> result = query.getWebsites("query");
        Assert.assertEquals("http://example.com/first",result.get(0).getUrl());
        result = query.getWebsites("first OR second");
        Assert.assertEquals("first",2, result.size());
        result = query.getWebsites("website for");
        Assert.assertEquals("second", 3, result.size());
        result = query.getWebsites("website second OR extra");
        Assert.assertEquals("third", 2, result.size());
        result = query.getWebsites("extra words");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("http://example.com/third", result.get(0).getUrl());
    }


    // Test for
        // duplicates
        // empty string or a single space " "
        // long strings
        // AND, OR, and " " separators
        // handling of the query "null"? (probably not necessary)
        //
        //
    // Should we count how many times the query is found on the website?
    // Are we counting how many websites it's found on? --> should be handled by the InvertedIndex Class - but is it?
        //
}
