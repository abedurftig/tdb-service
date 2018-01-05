package org.tdb.input.junit4;

import org.tdb.input.InputParser;
import org.tdb.input.junit4.model.Testsuites;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestsuitesInputParser extends InputParser<Testsuites> {

    @Override
    public Testsuites parseXML(String pathname) throws InputParseException {

        Testsuites testsuites = null;

        try {
            testsuites = parseXML(new FileInputStream(new File(pathname)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return testsuites;

    }

    @Override
    public Testsuites parseXML(InputStream stream) throws InputParseException {

        Testsuites testsuites = null;

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Testsuites.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            testsuites = (Testsuites) jaxbUnmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return testsuites;

    }

}
