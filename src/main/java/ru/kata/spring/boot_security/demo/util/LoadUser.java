package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

//package ru.kata.spring.boot_security.demo.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import javax.annotation.PostConstruct;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class LoadUser {
//    private final UserService userService;
//    private final RoleService roleService;
//
//    @Autowired
//    public LoadUser(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @Transactional
//    @PostConstruct
//    public void load() {
//
//        Role roleAdmin = new Role("ROLE_ADMIN");
//        Role roleUser = new Role("ROLE_USER");
//        Set<Role> adminSet = new HashSet<>();
//        Set<Role> userSet = new HashSet<>();
//
//        roleService.addRole(roleAdmin);
//        roleService.addRole(roleUser);
//
//        adminSet.add(roleAdmin);
//        adminSet.add(roleUser);
//
//
//        User admin = new User("Jenifer",
//                "Lopez",
//                53,
//                "gelo@gmail.com",
//                "gelo",
//                "100",
//                adminSet);
//
//        User user = new User("Mikel",
//                "Jordan",
//                60,
//                "jordan@mail.ru",
//                "red",
//                "50",
//                adminSet);
//
//
//        userService.saveUser(admin);
//        userService.saveUser(user);
//    }
//}
@Component
public class LoadUser implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoadUser(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role roleAdmin = new Role("ROLE_ADMIN");

            Role roleUser = new Role("ROLE_USER");
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);

            User user = new User();
            user.setName("Дженифер");
            user.setLastName("Лопез");
            user.setAge(53);
            user.setEmail("GeLo@mail.ru");
            user.setLogin("gelo");
            user.setPassword(passwordEncoder.encode("50"));
            user.setRoles(List.of(roleUser));
            userRepository.save(user);

            User admin = new User();
            admin.setName("Майкл");
            admin.setLastName("Джордан");
            admin.setAge(60);
            admin.setEmail("Jordan@mail.ru");
            admin.setLogin("red");
            admin.setPassword(passwordEncoder.encode("100"));
            admin.setRoles(List.of(roleAdmin, roleUser));
            userRepository.save(admin);
        }
    }
}