package searchengine;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by haoqwu on 10/10/2016.
 */
public class IndexTest {
    List<Website> sites = FileHelper.parseFile("test-resources/test-file.txt");
    InvertedIndexTreeMap tree = new InvertedIndexTreeMap();
    InvertedIndexHashMap hash = new InvertedIndexHashMap();
    SimpleIndex simple = new SimpleIndex();

    public void build() throws Exception {
        tree.Build(sites);
        hash.Build(sites);
        simple.Build(sites);

        assertEquals(false, tree.isEmpty());
        assertEquals(false, hash.isEmpty());
        assertEquals(false, simple.isEmpty());

        assertEquals(true, tree.containsKey("word2"));
        assertEquals(true, hash.containsKey("word2"));
        assertEquals(true, simple.containsKey("word2"));


        assertEquals(true, tree.containsValue("http://page2.com"));
        assertEquals(true, hash.containsValue("http://page2.com"));

        assertEquals(4, tree.size());
        assertEquals(4, hash.size());
        assertEquals(4, simple.size());
    }


    public void lookup() throws Exception {
        assertEquals("http://page2.com", tree.lookup("word3"));
        assertEquals("http://page2.com", hash.lookup("word3"));
        assertEquals("http://page2.com", simple.lookup("word3"));

    }

}