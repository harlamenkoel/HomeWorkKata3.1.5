package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImp userService;


    @Autowired
    public AdminController(UserServiceImp userService) {
        this.userService = userService;

    }

    @GetMapping(value = "")
    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getAllRoles());
        return "admin";
    }

    @GetMapping("/newUser")
    public String showNewUser(Model model) {
        User user = new User();
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "newUser";
    }


    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user, Model model) {

        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "edit";
    }


    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, Model model) {

        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);

        userService.updateUser(user);

        return "redirect:/admin";
    }


    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
