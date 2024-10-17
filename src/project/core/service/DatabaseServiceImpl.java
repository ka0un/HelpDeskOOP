package project.core.service;

import java.sql.Connection;


import project.core.service.interfaces.DatabaseService;
import project.core.util.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseServiceImpl implements DatabaseService {

    private DataSource dataSource;
    private static DatabaseServiceImpl instance;

    public DatabaseServiceImpl() {

        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/helpdeskDB");
        } catch (NamingException e) {
            throw new RuntimeException("Failed to initialize DataSource", e);
        }

    }

    public synchronized static DatabaseServiceImpl getInstance() {

        if (instance == null) {
            instance = new DatabaseServiceImpl();
        }

        return instance;
    }

    @Override
    public void init() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255) NOT NULL, "
                + "email VARCHAR(255) NOT NULL, "
                + "password VARCHAR(255) NOT NULL," +
                "role VARCHAR(255) NOT NULL"
                + ");" +
                "INSERT INTO users (id, name, email, password, role) \n" +
                "SELECT 9999, 'admin', 'admin@admin.com', 'admin', 'admin' \n" +
                "WHERE NOT EXISTS (SELECT 1 FROM users WHERE id = 9999);" + "CREATE TABLE IF NOT EXISTS video_tutorials (\r\n"
                		+ "    Id INT NOT NULL AUTO_INCREMENT,\r\n"
                		+ "    Title VARCHAR(255) NOT NULL,\r\n"
                		+ "    YoutubeUrl VARCHAR(255) NOT NULL,\r\n"
                		+ "    Views INT DEFAULT 0,\r\n"
                		+ "    UploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\r\n"
                		+ "    Category VARCHAR(100),\r\n"
                		+ "    Type VARCHAR(10) DEFAULT 'enable',\r\n"
                		+ "    PRIMARY KEY (Id)\r\n"
                		+ ");\r\n"
                		+ "";

        try (Connection connection = getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database", e);
        }

        Logger.log("Database connection successful");
    }

    @Override
    public Connection getConnection() {
        try {
            return getInstance().dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
    }

    public String getDatabaseUrl() {
        try {
            return getConnection().getMetaData().getURL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
