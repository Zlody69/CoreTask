package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Petya", "Ivanov", (byte) 21);
        userService.saveUser("Sasha", "Grey", (byte) 53);
        userService.saveUser("Barak", "Obama", (byte) 21);
        userService.saveUser("Evgeniy", "Petrovich", (byte) 21);
        userService.getAllUsers().stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
