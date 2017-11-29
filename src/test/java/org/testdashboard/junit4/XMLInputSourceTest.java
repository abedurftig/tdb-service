package org.testdashboard.junit4;

import org.junit.Assert;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static org.junit.Assert.fail;

/**
 * @author Arne
 * @since 05/11/2017
 */
public class XMLInputSourceTest {

    @Test
    public void testExtractPackageName() {

        String testName = "org.junitdashboard.xml.XMLInputSourceTest";
        Assert.assertEquals("extractPackageName() method",
                "org.junitdashboard.xml", new XMLInputSource().extractPackageName(testName));

    }

    @Test
    public void testExtractTimestamp() {

        String pattern = "MM/dd/yyyy - HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = "11/05/2017 - 20:56:30";

        GregorianCalendar gc = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = null;

        try {

            gc.setTime(sdf.parse(dateString));
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

        } catch (ParseException e) {
            fail("extractTimestamp() method: could not parse example date");
        } catch (DatatypeConfigurationException e) {
            fail("extractTimestamp() method: could not instantiate XMLGregorianCalendar");
        }

        Assert.assertEquals("extractTimestamp() method",
                dateString, sdf.format(new XMLInputSource().extractTimestamp(xmlDate)));

    }

}
