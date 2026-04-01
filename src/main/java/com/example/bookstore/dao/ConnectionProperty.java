package com.example.bookstore.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperty {
    private final String driverClass;
    private final String url;
    private final String login;
    private final String password;

    public ConnectionProperty() throws IOException {
        Properties props = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("config/config.properties");
        if (is == null) {
            throw new IOException("Configuration file not found: config/config.properties");
        }
        props.load(is);

        this.driverClass = props.getProperty("db.driver.class");
        this.url = props.getProperty("db.url");
        this.login = props.getProperty("db.login");
        this.password = props.getProperty("db.password");

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found: " + driverClass, e);
        }
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
