//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.06 at 11:06:31 PM CET 
//


package org.tdb.input.junit4.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.testdashboard.input.junit4.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Testsuite_QNAME = new QName("", "testsuite");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.testdashboard.input.junit4.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Testsuites }
     * 
     */
    public Testsuites createTestsuites() {
        return new Testsuites();
    }

    /**
     * Create an instance of {@link org.tdb.input.junit4.model.Testsuite }
     * 
     */
    public org.tdb.input.junit4.model.Testsuite createTestsuite() {
        return new org.tdb.input.junit4.model.Testsuite();
    }

    /**
     * Create an instance of {@link org.tdb.input.junit4.model.Testsuite.Testcase }
     * 
     */
    public org.tdb.input.junit4.model.Testsuite.Testcase createTestsuiteTestcase() {
        return new org.tdb.input.junit4.model.Testsuite.Testcase();
    }

    /**
     * Create an instance of {@link org.tdb.input.junit4.model.Testsuite.Properties }
     * 
     */
    public org.tdb.input.junit4.model.Testsuite.Properties createTestsuiteProperties() {
        return new org.tdb.input.junit4.model.Testsuite.Properties();
    }

    /**
     * Create an instance of {@link Testsuites.Testsuite }
     * 
     */
    public Testsuites.Testsuite createTestsuitesTestsuite() {
        return new Testsuites.Testsuite();
    }

    /**
     * Create an instance of {@link org.tdb.input.junit4.model.Testsuite.Testcase.Error }
     * 
     */
    public org.tdb.input.junit4.model.Testsuite.Testcase.Error createTestsuiteTestcaseError() {
        return new org.tdb.input.junit4.model.Testsuite.Testcase.Error();
    }

    /**
     * Create an instance of {@link org.tdb.input.junit4.model.Testsuite.Testcase.Failure }
     * 
     */
    public org.tdb.input.junit4.model.Testsuite.Testcase.Failure createTestsuiteTestcaseFailure() {
        return new org.tdb.input.junit4.model.Testsuite.Testcase.Failure();
    }

    /**
     * Create an instance of {@link org.tdb.input.junit4.model.Testsuite.Testcase.Skipped }
     * 
     */
    public org.tdb.input.junit4.model.Testsuite.Testcase.Skipped createTestsuiteTestcaseSkipped() {
        return new org.tdb.input.junit4.model.Testsuite.Testcase.Skipped();
    }

    /**
     * Create an instance of {@link org.tdb.input.junit4.model.Testsuite.Properties.Property }
     * 
     */
    public org.tdb.input.junit4.model.Testsuite.Properties.Property createTestsuitePropertiesProperty() {
        return new org.tdb.input.junit4.model.Testsuite.Properties.Property();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.tdb.input.junit4.model.Testsuite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "testsuite")
    public JAXBElement<org.tdb.input.junit4.model.Testsuite> createTestsuite(org.tdb.input.junit4.model.Testsuite value) {
        return new JAXBElement<org.tdb.input.junit4.model.Testsuite>(_Testsuite_QNAME, org.tdb.input.junit4.model.Testsuite.class, null, value);
    }

}
