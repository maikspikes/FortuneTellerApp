package org.example.Infra.presentation;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.domain.Fortune;
import spark.Spark;
import spark.Spark.*;

import java.io.IOException;
import java.sql.SQLException;

public class fortuneTeller {
    public static void main(String[] args) throws SQLException {
        Spark.get("/hello", (req, res) -> "Hello World");

        String username = "fortune";
        String password = "fortune";

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
