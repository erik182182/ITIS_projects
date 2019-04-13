package com.sera.wellness.services;

import com.sera.wellness.forms.ArticleAddForm;
import com.sera.wellness.models.Article;
import com.sera.wellness.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository repository;

    public List<Article> getAll() {
        List<Article> articles = repository.findAll();
//        for (int i = 0; i < articles.size(); i ++) {
//            String[] resources = articles.get(i).getResources();
//            resources[0] = "articles/"+resources[0];
//        }
        return articles;
    }

    public Article getArticle(Long id) {
        Optional<Article> articleCandidate = repository.findOne(id);
        if(articleCandidate.isPresent()) return articleCandidate.get();
        else {
            throw new IllegalArgumentException("Такой статьи не существует.");
        }
//        Optional<Article> articleCandidat  = repository.findOne(id);
//        if (articleCandidat.isPresent()) {
//            String[] resources = articleCandidat.get().getResources();
//            for (int i = 0; i < resources.length; i ++) {
//                resources[i] = "articles/" + resources[i];
//            }
//        }
//        return articleCandidat;
    }

    public void addArticle(ArticleAddForm form) {
        Article article = Article.builder()
                .title(form.getTitle())
                .text(form.getText())
                .user()
                .build();
        repository.save(article);
    }
}
