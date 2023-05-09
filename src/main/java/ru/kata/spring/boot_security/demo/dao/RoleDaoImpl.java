package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleById(Long id) {
        return (Role) entityManager.createQuery("SELECT r FROM Role r WHERE r.id = :id")
                .setParameter("id", id)
                .getResultList()
                .get(0);

    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList());
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);

    }
}
