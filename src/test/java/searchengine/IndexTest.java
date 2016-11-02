package searchengine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import sun.jvm.hotspot.jdi.ArrayReferenceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Leonid on 25/10/16.
 */
public class IndexTest {

    private Index simpleIndex;
    private Index invertedIndex;

    @Before
    public void setUp() {
        simpleIndex = new SimpleIndex();
        invertedIndex = new InvertedIndex(new HashMap<>());

        List<String> words1 = Arrays.asList("This", "is", "a", "first", "website", "just", "a", "test");
        List<String> words2 = Arrays.asList("This", "is", "a", "second", "website");

        Website website1 = new Website("http://example.com/first", "first", words1);
        Website website2 = new Website("http://example.com/seconbd", "second", words2);

        simpleIndex.build(Arrays.asList(website1, website2));
        invertedIndex.build(Arrays.asList(website1, website2));
    }

    @After
    public void tearDown() {
        simpleIndex = null;
        invertedIndex = null;
    }

    @Test
    public void buildTestSimpleIndex(){
        Assert.assertEquals("SimpleIndex{sites=[Website{url='http://example.com/first', title='first', words=[This, is, a, first, website, just, a, test]}, Website{url='http://example.com/seconbd', title='second', words=[This, is, a, second, website]}]}", simpleIndex.toString());

    }

    @Test
    public void simpleIndexLookupTest() {
        lookupTest(simpleIndex);
    }

    @Test
    public void invetedIndexLookupTest() {
        //uncomment after implementing the inverted index
        //lookupTest(invertedIndex);
    }

    private void lookupTest(Index index) {
        List<Website> result = index.lookup("This");
        Assert.assertEquals(2, result.size());

        result = index.lookup("just");
        Assert.assertEquals(1, result.size());

        Assert.assertEquals("http://example.com/first", result.get(0).getUrl());

        result = index.lookup("itu");
        Assert.assertNotEquals(null, result);
        Assert.assertEquals(0, result.size());
    }

}