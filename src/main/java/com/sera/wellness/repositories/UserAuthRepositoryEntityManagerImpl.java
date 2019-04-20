package com.sera.wellness.repositories;

import com.sera.wellness.models.Article;
import com.sera.wellness.models.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
@Repository
public class UserAuthRepositoryEntityManagerImpl implements UserAuthRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(UserAuth model) {
        em.persist(model);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.remove(Article.builder().id(id).build());
    }

    @Override
    @Transactional
    public void update(UserAuth model) {
        em.merge(model);
    }

    @Override
    public Optional<UserAuth> findOne(Long id) {
        return Optional.ofNullable(em.find(UserAuth.class,id));
    }

    @Override
    public List<UserAuth> findAll() {
        return null;
    }
}
