package org.example.Infra.presentation;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.domain.Fortune;
import spark.Spark;
import spark.Spark.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class fortuneTeller {
    public static void main(String[] args) throws SQLException {
        Spark.get("/hello", (req, res) -> "Hello World");

        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        String databaseUrl = "jdbc:postgresql://localhost:5433/fortunetellerapp";

        // create a connection source to our database
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl, username, password);

        // instantiate the DAO to handle Fortune class with String id
        Dao<Fortune, String> fortuneDao = DaoManager.createDao(connectionSource, Fortune.class);

        // if you need to create the 'fortunes' table make this call
        if (!fortuneDao.isTableExists()) {
            TableUtils.createTableIfNotExists(connectionSource, Fortune.class);
        }

        // Close the connection source
        try {
            connectionSource.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
