import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by haoqwu on 10/10/2016.
 */
public class IndexTest {
    List<Website> sites = FileHelper.parseFile("test-resources/test-file.txt");


    public void build() throws Exception {
        InvertedIndexHashMap.Build(sites);
        InvertedIndexTreeMap.Build(sites);
        SimpleIndex.Build(sites);

        assertEquals(false, InvertedIndexHashMap.isEmpty());
        assertEquals(false, InvertedIndexTreeMap.isEmpty());
        assertEquals(false, SimpleIndex.isEmpty());

        assertEquals(true, InvertedIndexHashMap.containsKey("word2"));
        assertEquals(true, InvertedIndexTreeMap.containsKey("word2"));
        assertEquals(true, SimpleIndex.containsKey("word2"));


        assertEquals(true, InvertedIndexHashMap.containsValue("http://page2.com"));
        assertEquals(true, InvertedIndexTreeMap.containsValue("http://page2.com"));

        assertEquals(4, InvertedIndexHashMap.size());
        assertEquals(4, InvertedIndexTreeMap.size());
        assertEquals(4, SimpleIndex.size());
    }


    public void lookup() throws Exception {
        assertEquals("http://page2.com", Index.lookup("word3"));

    }

}