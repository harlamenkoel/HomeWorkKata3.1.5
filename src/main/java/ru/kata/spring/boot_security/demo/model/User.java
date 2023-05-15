package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Поле имя не должно быть пустым")
    @Size(min = 2, max = 10, message = "Поле имя должно быть длиной от 2 до 10 символов")
    private String name;

    @Column(name = "lastname")
    @NotEmpty(message = "Поле фамилия не должно быть пустым")
    @Size(min = 2, max = 10, message = "Поле фамилия должно быть длиной от 2 до 10 символов")
    private String lastName;

    @Column(name = "age")
    @Min(value = 18, message = "Вам должно быть минимум 18 лет")
    @Max(value = 99, message = "Ваш возраст не должен превышать 99 лет")
    private Integer age;

    @Column(name = "email")
    @Email(message = "Не верный формат поля email")
    private String email;

    @Column(name = "login")
    @NotEmpty(message = "Поле логин не должно быть пустым")
    private String login;


    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String name,
                String lastName,
                Integer age,
                String email,
                String login,
                String password,
                Collection<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Пользователь: " + id + " " + name + " " + lastName + " " + age + " " + email + " " + login + " " + password + " " + roles;
    }
}
