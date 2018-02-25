package org.tdb.input.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.tdb.input.junit4.model.Testsuite;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void xmlParserParsesFailure() {

        try {

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("TEST-org.junitdashboard.xml.XMLInputParserTest.xml");
            Testsuite suite = new TestsuiteInputParser().parseXML(is);

            Assert.assertEquals("name", "org.junitdashboard.xml.XMLInputParserTest", suite.getName());
            Assert.assertEquals("number of test cases", 4, suite.getTestcase().size());

            List<Testsuite.Testcase> testcasesWithFailure =
                    suite.getTestcase().stream().filter(testcase -> testcase.getFailure() != null)
                            .collect(Collectors.toList());

            assertThat(testcasesWithFailure.size()).isEqualTo(2).as("number of failures");
            assertThat(testcasesWithFailure.get(0).getFailure().getMessage())
                    .isNotNull().isNotBlank().isNotEmpty().as("should have message");

            Testsuite.Testcase testcaseThree = suite.getTestcase()
                    .stream().filter(testcase -> testcase.getName().equals("testToString3"))
                    .findFirst().get();

            assertThat(testcaseThree.getFailure().getValue()).contains("java.lang.IllegalStateException: Haha!").as("failure value");

        } catch (Exception e) {
            fail("Should not throw exception.");
        }

    }

    @Test
    public void xmlParserParsesSkipped() {

        try {

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("TEST-org.junitdashboard.xml.XMLInputParserTest.xml");
            Testsuite suite = new TestsuiteInputParser().parseXML(is);

            Assert.assertEquals("name", "org.junitdashboard.xml.XMLInputParserTest", suite.getName());

            List<Testsuite.Testcase> testcasesWithFailure =
                    suite.getTestcase().stream().filter(testcase -> testcase.getSkipped() != null)
                            .collect(Collectors.toList());

            assertThat(testcasesWithFailure.size()).isEqualTo(1).as("number of skipped");

        } catch (Exception e) {
            fail("Should not throw exception.");
        }

    }

}
