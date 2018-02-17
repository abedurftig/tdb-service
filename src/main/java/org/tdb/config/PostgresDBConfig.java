package org.tdb.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("pg-standalone")
public class PostgresDBConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresDBConfig.class);

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {

        BasicDataSource basicDataSource = null;

        String url = System.getenv("DATABASE_URL");
        if (url != null) {
            basicDataSource = fromUrl(url);
        } else {
            String dbUser = System.getenv("DATABASE_USER");
            String dbPassword = System.getenv("DATABASE_PASSWORD");
            String dbHost = System.getenv("DATABASE_HOST");
            String dbPort = System.getenv("DATABASE_PORT");
            String dbName = System.getenv("DATABASE_NAME");
            basicDataSource = fromComponents(dbUser, dbPassword, dbHost, dbPort, dbName);
        }

        LOGGER.info("Connecting to DB {} as user {}", basicDataSource.getUrl(), basicDataSource.getUsername());

        return basicDataSource;

    }

    static BasicDataSource fromUrl(String url) throws URISyntaxException {

        URI uri = new URI(url);
        String username = uri.getUserInfo().split(":")[0];
        String password = uri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + uri.getHost() + ':' + uri.getPort() + uri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;

    }

    static BasicDataSource fromComponents(String dbUser, String dbPassword, String dbHost, String dbPort, String dbName) {

        String dbUrl = "jdbc:postgresql://" + dbHost + ':' + dbPort + "/" + dbName;

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(dbUser);
        basicDataSource.setPassword(dbPassword);

        return basicDataSource;

    }

}
