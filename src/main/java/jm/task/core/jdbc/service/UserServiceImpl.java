package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao daoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        daoJDBC.createUsersTable();
        System.out.println("Таблица users созданна");

    }

    public void dropUsersTable() {
        daoJDBC.dropUsersTable();
        System.out.println("Таблица users удаленна");


    }

    public void saveUser(String name, String lastName, byte age) {
        daoJDBC.saveUser(name, lastName, age);
        System.out.printf("User с именем – %s добавлен в базу данных\n",name);

    }

    public void removeUserById(long id) {
        daoJDBC.removeUserById(id);
        System.out.println("Строка из таблицы users удаленна");


    }

    public List<User> getAllUsers() {
        return daoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        daoJDBC.cleanUsersTable();
        System.out.println("Таблица users очищенна");

    }
}
