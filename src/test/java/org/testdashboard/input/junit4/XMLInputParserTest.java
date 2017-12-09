package org.testdashboard.input.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.testdashboard.input.junit4.model.Testsuite;

import java.io.InputStream;

/**
 * @author Arne
 * @since 05/11/2017
 */
public class XMLInputParserTest {

    @Test
    public void xmlParserSanityCheck() {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("TEST-org.junitdashboard.ApplicationTests.xml");
        Testsuite suite = new XMLInputParser().parseXML(is);

        Assert.assertEquals("name", "org.junitdashboard.ApplicationTests", suite.getName());

    }

}
