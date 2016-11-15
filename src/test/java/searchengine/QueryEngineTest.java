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
    private QueryEngine naiveQuery;
    private QueryEngine orderedQuery;
    private Score simpleScore;
    private Score tfScore;
    private List<Website> result;

    @Before
    public void setUp() {
        index = new InvertedIndex(new HashMap());
        List<String> words1 = Arrays.asList("first","website", "for", "testing", "the", "query");
        List<String> words2 = Arrays.asList("second", "website", "for", "testing", "with", "some", "different", "words");
        List<String> words3 = Arrays.asList("third", "website", "for", "testing", "with", "extra", "words", "for", "testing", "more");

        Website website1 = new Website("http://example.com/first", "first", words1);
        Website website2 = new Website("http://example.com/second", "second", words2);
        Website website3 = new Website("http://example.com/third", "third", words3);

        index.build(Arrays.asList(website1, website2, website3));
        simpleScore = new SimpleScore();
        naiveQuery = new QueryEngine(index, simpleScore);

        tfScore = new TFScore();
        orderedQuery = new QueryEngine(index, tfScore);

    }
    @After
    public void tearDown() {
        index = null;
        simpleScore = null;
        naiveQuery = null;

    }

    @Test
    public void queryTest() {
        // simple naiveQuery
        result = naiveQuery.getWebsites("query");
        Assert.assertEquals("simpe naiveQuery", "http://example.com/first",result.get(0).getUrl());

        // tests OR
        result = naiveQuery.getWebsites("first OR second");
        Assert.assertEquals("OR simple query",2, result.size());
        result = naiveQuery.getWebsites("with OR the");
        Assert.assertEquals("OR simple query second time", 3, result.size());

        // tests AND
        result = naiveQuery.getWebsites("website for");
        Assert.assertEquals("AND simple query", 3, result.size());
        result = naiveQuery.getWebsites("website for");
        Assert.assertEquals("AND simple query second time", 3, result.size());

        // tests AND and OR
        result = naiveQuery.getWebsites("website second OR extra");
        Assert.assertEquals("Complex query size", 2, result.size());

        // we were having a bug when searching for another simple naiveQuery the second time, therefore this test again.
        result = naiveQuery.getWebsites("query");
        Assert.assertEquals("simpe query second time", "http://example.com/first",result.get(0).getUrl());
    }

    @Test
    public void queryOrderTest(){
        result = orderedQuery.getWebsites("testing");
        Assert.assertEquals("simple query", "http://example.com/third", result.get(0).getUrl());
        
    }

}
