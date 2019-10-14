import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of CodonCountTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCountTest {
    private CodonCount cc;

    @Before
    public void setUp() throws Exception {
	this.cc = new CodonCount();
    }

    @After
    public void tearDown() throws Exception {
	this.cc = null;
    }

    @Test
    public void test() {
	cc.tester();
    }
}
