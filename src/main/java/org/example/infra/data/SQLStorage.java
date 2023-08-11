package org.example.infra.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.domain.Fortune;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class SQLStorage {

    private ConnectionSource connectionSource;
    private Dao<Fortune, String> fortuneDao;
    public SQLStorage(Properties properties) {
        initializeConnection(properties);
        initializeDao();
        createTableIfNotExists();
    }

    private void initializeConnection(Properties properties) {
        try {
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String databaseUrl = properties.getProperty("db.url");

            connectionSource = new JdbcConnectionSource(databaseUrl, username, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }



    // instantiate the DAO to handle Fortune class with String id

    private void initializeDao() {
        try {
            fortuneDao = DaoManager.createDao(connectionSource, Fortune.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // if you need to create the 'fortunes' table make this call
    private void createTableIfNotExists() {
        try {
            if (!fortuneDao.isTableExists()) {
                TableUtils.createTableIfNotExists(connectionSource, Fortune.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating table", e);
        }
    }

    // Close the connection source
    public void closeConnection() {
        try {
            connectionSource.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing connection", e);
        }
    }




}
