package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    static private final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static private final String USER = "root";
    static private final String PASSWORD = "526549Aa`";

    public Util() {
    }

     public static Connection utilConnection(){
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            return connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }



}
