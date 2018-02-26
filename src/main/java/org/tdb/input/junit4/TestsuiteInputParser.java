package org.tdb.input.junit4;

import org.tdb.input.InputParser;
import org.tdb.input.junit4.model.Testsuite;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestsuiteInputParser extends InputParser<Testsuite> {

    public Testsuite parseXML(String pathname) throws InputParseException {

        Testsuite testsuite = null;

        try {
            testsuite = parseXML(new FileInputStream(new File(pathname)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return testsuite;

    }

    public Testsuite parseXML(InputStream stream) throws InputParseException {

        Testsuite testsuite = null;

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Testsuite.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            testsuite = (Testsuite) jaxbUnmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return testsuite;

    }

}
