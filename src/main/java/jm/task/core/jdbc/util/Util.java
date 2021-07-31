package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.HiberUser;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.*;
import java.util.Properties;

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

    public static SessionFactory utillHibern() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, JDBC_DRIVER);
        properties.setProperty(Environment.URL, DATABASE_URL);
        properties.setProperty(Environment.USER, USER);
        properties.setProperty(Environment.PASS, PASSWORD);
        //create - автоматически создает пустые таблицы в базе данных, из указаных Entity классов
        //update - если нет такой таблицы автоматически создает, если есть обновляет
//        properties.setProperty(Environment.HBM2DDL_AUTO, "update");

        SessionFactory sessionFactory = new Configuration().
                addProperties(properties).
                addAnnotatedClass(HiberUser.class).
                addAnnotatedClass(User.class).
                buildSessionFactory();
        return sessionFactory;
    }



}
