import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of CharactersInPlayTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlayTest {
    private CharactersInPlay cn;

    @Before
    public void setUp() throws Exception {
        this.cn = new CharactersInPlay();
    }

    @After
    public void tearDown() throws Exception {
        this.cn = null;
    }

    @Test
    public void test() {
        cn.tester();
    }

}
