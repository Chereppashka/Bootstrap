package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> listUser();
    void saveUser(User user);
    void deleteUser(Long id);
    User findUser(Long id);
    void updateUser(Long id, User user);
    List<Role> findRoles();

    User findByUsername(String name);
}