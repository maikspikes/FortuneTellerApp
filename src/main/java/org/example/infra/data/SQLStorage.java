package org.example.infra.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.domain.Fortune;
import org.example.domain.FortuneStorage;

import java.sql.SQLException;
import java.util.Properties;

public class SQLStorage implements FortuneStorage {
    private ConnectionSource connectionSource;

    private Dao<Fortune, String> fortuneDao;
    public SQLStorage(Properties properties) {
        initializeConnection(properties);
        initializeDao();
        createTableIfNotExists();
    }

    @Override
    public Fortune getRandomFortune() {

        return new Fortune(100, "Ik ga dit fixen");
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

    private void initializeDao() {
        try {
            fortuneDao = DaoManager.createDao(connectionSource, Fortune.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createTableIfNotExists() {
        try {
            if (!fortuneDao.isTableExists()) {
                TableUtils.createTableIfNotExists(connectionSource, Fortune.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating table", e);
        }
    }
}
