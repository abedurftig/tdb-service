package org.testdashboard.junit4;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.testdashboard.junit4.model.Testsuite;

import java.io.InputStream;

/**
 * @author Arne
 * @since 05/11/2017
 */
public class XMLInputParserTest {

    @Test
    public void testToString() {
        Assert.assertEquals("toString() method:", "This is the 'XMLInputParser'", new XMLInputParser().toString());
    }

    @Test
    @Ignore
    public void testToString4() {
        throw new IllegalStateException("Ignored!");
    }

    @Test
//    @Ignore
    public void testXMLtoModel() {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("TEST-org.junitdashboard.ApplicationTests.xml");
        Testsuite suite = new XMLInputParser().parseXML(is);

        Assert.assertEquals("name", "org.junitdashboard.ApplicationTests", suite.getName());
        Assert.assertNotNull("message", suite.getSystemOut());

    }

}
