package com.sera.wellness.repositories;

import com.sera.wellness.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRepositoryEntityManagerImpl implements UserRepository {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<User> findByEmail(String email) {
        Query query = em.createQuery("SELECT u from User u where u.email = :email");
        query.setParameter("email",email);
        return Optional.ofNullable((User) query.getSingleResult());
    }

    @Override
    @Transactional
    public void save(User model) {
        em.merge(model);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.persist(User.builder().id(id).build());
    }

    @Override
    @Transactional
    public void update(User model) {
        em.merge(model);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.ofNullable(em.find(User.class,id));
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT a FROM User a").getResultList();
    }
}
