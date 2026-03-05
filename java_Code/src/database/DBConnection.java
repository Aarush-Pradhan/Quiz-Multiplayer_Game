package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/quizDB";
        String user = "root";
        String password = "280082";

        return DriverManager.getConnection(url, user, password);
    }
}