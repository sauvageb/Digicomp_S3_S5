package fr.sauvageb.chat.dao.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL_DATABASE = "jdbc:mysql://localhost:8889/jakarta_jdbc_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection INSTANCE;

    private ConnectionManager() {
        // avoid instanciate
    }

    public static Connection getInstance() {
        if (INSTANCE == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                INSTANCE = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return INSTANCE;
    }

    public static void closeConnection() throws SQLException {
        if (INSTANCE != null && !INSTANCE.isClosed()) {
            INSTANCE.close();
        }
    }

}
