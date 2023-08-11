package org.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.example.application.FortuneTellerService;
import org.example.domain.Fortune;
import org.example.domain.FortuneStorage;
import org.example.infra.data.SQLStorage;
import org.example.infra.presentation.FortuneController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        // Get configuration
        InputStream config = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(config);

        // Create necessary services and inject dependencies
        // Our application is a composition of services
        FortuneStorage storage = new SQLStorage(properties);
        FortuneTellerService service = new FortuneTellerService(storage);
        FortuneController controller = new FortuneController(service);

        // Start our web application
        controller.listen(1337);
    }
}
