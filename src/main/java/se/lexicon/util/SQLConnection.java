package se.lexicon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static final String DB_NAME = "world";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD);
            System.out.println("Connected so DB " + DB_NAME + " established");
            return connection;
        }catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }
}
