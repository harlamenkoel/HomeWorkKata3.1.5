package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.ModelUserDetailsImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        ModelUserDetailsImp user = (ModelUserDetailsImp) ((Authentication) principal).getPrincipal();
        List<User> users = Collections.singletonList(userService.getUserById(user.getId()));
        model.addAttribute("user", users);
        return "/user";
    }
}