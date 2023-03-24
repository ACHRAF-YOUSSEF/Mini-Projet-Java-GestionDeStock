package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;

    private SingletonConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/gestion_de_stock",
                            "root",
                            "admin");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (connection == null) {
            new SingletonConnection();
        }

        return connection;
    }
}
