package ru.kata.spring.boot_security.demo.service;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


    @Override
    @Transactional
    public void updateUser(User updateUser) {
        String encodedPassword = passwordEncoder.encode(updateUser.getPassword());
        updateUser.setPassword(encodedPassword);
        userRepository.save(updateUser);

    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Hibernate.initialize(user.getRoles());
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    Hibernate.initialize(user.getRoles());
                    return user;
                })
                .orElse(null);
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


}
