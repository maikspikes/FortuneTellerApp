package org.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.domain.Fortune;
import org.example.infra.data.SQLStorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream config = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(config);

        // Perform database operations using sqlStorage


        SQLStorage sqlStorage = new SQLStorage(properties);
        sqlStorage.closeConnection();

    }
}
