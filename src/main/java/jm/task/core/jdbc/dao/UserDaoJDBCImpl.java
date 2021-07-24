package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = Util.utilConnection().createStatement();
            String sql = "create table users\n" +
                    "(\n" +
                    "\tid int auto_increment,\n" +
                    "\tname varchar(30) null,\n" +
                    "\tlastName varchar(30) null,\n" +
                    "\tage int null,\n" +
                    "\t\tprimary key (id)\n" +
                    ")";
            statement.execute(sql);
        } catch (SQLException throwables) {
            System.out.println("Не удалось создать, Таблица users уже создана");
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.utilConnection().createStatement();
            String sql = "drop table Users";
            statement.execute(sql);
        } catch (SQLException throwables) {
            System.out.println("Не удалось удалить, Таблицы users нет");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name, lastName, age) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = Util.utilConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try {
            PreparedStatement preparedStatement = Util.utilConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        String sql = "select * from users";
        try {
            Statement statement = Util.utilConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultList.add(new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.utilConnection().createStatement();
            statement.execute("truncate table users");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
