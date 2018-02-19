package org.tdb.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;

import static junit.framework.TestCase.fail;

public class PostgresDBConfigTests {

    @Test
    public void testDataSourceCreationFromUrl() {

        String url = "postgres://dbuser:dbpw@databasehost:9999/dbname";
        try {

            BasicDataSource basicDataSource = PostgresDBConfig.fromUrl(url);

            Assert.assertEquals("url", "jdbc:postgresql://databasehost:9999/dbname", basicDataSource.getUrl());
            Assert.assertEquals("user", "dbuser", basicDataSource.getUsername());
            Assert.assertEquals("password", "dbpw", basicDataSource.getPassword());

        } catch (URISyntaxException e) {
            fail("threw exception");
        }

    }

    @Test
    public void testDataSourceCreationFromComponents() {

        BasicDataSource basicDataSource = PostgresDBConfig.fromComponents("user", "password",
                "host","port", "db");

        Assert.assertEquals("url", "jdbc:postgresql://host:port/db", basicDataSource.getUrl());
        Assert.assertEquals("user", "user", basicDataSource.getUsername());
        Assert.assertEquals("password", "password", basicDataSource.getPassword());

    }

}
