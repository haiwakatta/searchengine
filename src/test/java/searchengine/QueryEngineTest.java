package searchengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Stefan on 5/11/16.
 *
 * @author Stefan Wachmann
 */
public class QueryEngineTest {
    private String longQuery1;
    private String longQuery2;
    private String separatorsQuery1;
    private String separatorsQuery2;
    private String emptyQuery;



    @Before
    public void setUp() {
        longQuery1 = "Where are the absolute best restaurants in Copenhagen";
        longQuery2 = "various of the extremely largescale states are selfgoverning";
        separatorsQuery1 = "commonly AND country OR happiest zealand OR  ";
        separatorsQuery2 = "ituniversitetet OR and AND or OR https";
        emptyQuery = "";

    }
    @After
    public void tearDown() {
        longQuery1 = null;
        longQuery2 = null;
        separatorsQuery1 = null;
        separatorsQuery2 = null;
        emptyQuery = null;

    }

    @Test
    public void doThisTest() {

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
