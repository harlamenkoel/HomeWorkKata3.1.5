package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleDao {

    Role getRoleById(Long id);

    Set<Role> getAllRoles();

    void addRole(Role role);
}
