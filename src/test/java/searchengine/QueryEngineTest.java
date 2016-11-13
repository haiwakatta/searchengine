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


    }
    @After
    public void tearDown() {
        index = null;
        query = null;

    }

    @Test
    public void queryTest() {
        List<Website> result = query.getWebsites("query");
        Assert.assertEquals("simpe query", "http://example.com/first",result.get(0).getUrl());

        result = query.getWebsites("first OR second");
        Assert.assertEquals("OR simple query",2, result.size());

        result = query.getWebsites("website for");
        Assert.assertEquals("AND simple query", 3, result.size());

        result = query.getWebsites("website second OR extra");
        Assert.assertEquals("Complex query size", 2, result.size());
        Assert.assertEquals("Complex query first website", "http://example.com/second", result.get(0).getUrl() );

        result = query.getWebsites("query");
        Assert.assertEquals("simpe query second time", "http://example.com/first",result.get(0).getUrl());

        result = query.getWebsites("website for");
        Assert.assertEquals("AND simple query second time", 3, result.size());
    }

}
