package template;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class TestTemplatePerformance {

    Template template;

    @Before
    public void setUp() throws Exception {
        template = new Template("The ${social} ${jelly} ${discriminates} above a ${general} advice." + 
        "An ${alpha} ${biology} ${catalogs} a ${treasure}. The ${laser} works outside a goodbye! The mistaken trace solos without the touch." + 
        "The social jelly discriminates above a general advice. An alpha biology catalogs a treasure. The laser works outside a" +
        "goodbye! The mistaken trace solos without the touch.");
        template.set("social", "social");
        template.set("jelly", "jelly");
        template.set("discriminates", "discriminates");
        template.set("general", "general");
        template.set("alpha", "alpha");
        template.set("biology", "biology");
        template.set("catalogs", "catalogs");
        template.set("treasure", "treasure");
        template.set("laser", "laser");
    }

    @Test
    public void templateWith100WordsAnd20Variables() throws Exception {
        long expected = 200L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue("Rendering the template took " + time
                + "ms while the target was " + expected + "ms",
                time <= expected);
    }
}
