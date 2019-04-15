package com.sera.wellness.repositories;

import com.sera.wellness.models.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRepositoryEntityManagerImpl implements ArticleRepository {
    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public void save(Article model) {
        System.out.println("name" + model.getUser().getFirstName());
        em.persist(model);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.remove(Article.builder().id(id).build());
    }

    @Override
    @Transactional
    public void update(Article model) {
        em.merge(model);
    }

    @Override
    public Optional<Article> findOne(Long id) {
        return Optional.ofNullable(em.find(Article.class,id));
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("SELECT a FROM Article a").getResultList();
    }
}
