package org.tdb.input.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.tdb.input.junit4.model.Testsuites;

import java.io.InputStream;

import static org.junit.Assert.fail;

public class TestsuitesInputParserTest {

    @Test
    public void xmlParserSanityCheck() {

        try {

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("test-results.xml");
            Testsuites suites = new TestsuitesInputParser().parseXML(is);
            Testsuites.Testsuite suite = suites.getTestsuite().get(0);

            Assert.assertEquals("name", "/src/app/reducers/projects.test.js", suite.getName());

        } catch (Exception e) {
            fail("Should not throw exception.");
        }

    }

}