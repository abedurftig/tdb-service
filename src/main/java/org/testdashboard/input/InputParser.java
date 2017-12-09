package org.testdashboard.input;

import java.io.InputStream;

/**
 * @author Arne
 * @since 06/12/2017
 */
public abstract class InputParser<I> {

    public abstract I parseXML(String pathname) throws InputParseException;

    public abstract I parseXML(InputStream stream) throws InputParseException;

    public static class InputParseException extends Exception {

        private String exceptionType;

        private String originalMessage;

        public InputParseException(Exception e) {

            this.exceptionType = e.getClass().getTypeName();
            this.originalMessage = e.getMessage();

        }

    }

}
