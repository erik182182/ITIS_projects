package com.sera.wellness.services;

import com.sera.wellness.models.Article;
import com.sera.wellness.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository repository;

    public List<Article> getAll() {
        List<Article> articles = repository.findAll();
        for (int i = 0; i < articles.size(); i ++) {
            String[] resources = articles.get(i).getResources();
            resources[0] = "articles/"+resources[0];
        }
        return articles;
    }

    public Optional<Article> getOne(Long id) {

        Optional<Article> articleCandidat  = repository.findOne(id);
        if (articleCandidat.isPresent()) {
            String[] resources = articleCandidat.get().getResources();
            for (int i = 0; i < resources.length; i ++) {
                resources[i] = "articles/" + resources[i];
            }
        }
        return articleCandidat;
    }

    public void addArticle(Article article){
        repository.save(article);
    }
}
