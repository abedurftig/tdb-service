package org.testdashboard.junit4;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.testdashboard.junit4.model.Testsuite;

import java.io.*;

/**
 * @author Arne
 * @since 05/11/2017
 */
public class XMLInputParser {

    public Testsuite parseXML(String pathname) {

        Testsuite suite = null;

        try {
            suite = parseXML(new FileInputStream(new File(pathname)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return suite;

    }

    public Testsuite parseXML(InputStream stream) {

        Testsuite suite = null;

        try {

            String xml = inputStreamToString(stream);
            suite = new XmlMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true)
                    .readValue(xml, Testsuite.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return suite;

    }

    private static String inputStreamToString(InputStream is) throws IOException {

        String line;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();

        return sb.toString();

    }

    @Override
    public String toString() {
        return "This is the 'XMLInputParser'";
    }

}
