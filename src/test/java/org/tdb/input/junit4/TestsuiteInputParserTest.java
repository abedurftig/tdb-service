package org.tdb.input.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.tdb.input.junit4.model.Testsuite;

import java.io.InputStream;

import static org.junit.Assert.fail;

/**
 * @author Arne
 * @since 05/11/2017
 */
public class TestsuiteInputParserTest {

    @Test
    public void xmlParserSanityCheck() {

        try {

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("TEST-org.junitdashboard.ApplicationTests.xml");
            Testsuite suite = new TestsuiteInputParser().parseXML(is);

            Assert.assertEquals("name", "org.junitdashboard.ApplicationTests", suite.getName());

        } catch (Exception e) {
            fail("Should not throw exception.");
        }

    }

}
