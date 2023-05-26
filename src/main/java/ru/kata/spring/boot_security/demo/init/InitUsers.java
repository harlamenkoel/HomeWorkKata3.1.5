package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import java.util.List;

@Component
public class InitUsers implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitUsers(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role roleAdmin = new Role("ROLE_ADMIN");

            Role roleUser = new Role("ROLE_USER");
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);

            User user = new User();
            user.setName("Дженифер");
            user.setSurname("Лопез");
            user.setAge(53);
            user.setEmail("gelo@mail.ru");
            user.setPassword(passwordEncoder.encode("50"));
            user.setRoles(List.of(roleUser));
            userRepository.save(user);

            User admin = new User();
            admin.setName("Майкл");
            admin.setSurname("Джордан");
            admin.setAge(60);
            admin.setEmail("red@yandex.ru");
            admin.setPassword(passwordEncoder.encode("100"));
            admin.setRoles(List.of(roleAdmin, roleUser));
            userRepository.save(admin);
        }
    }
}
