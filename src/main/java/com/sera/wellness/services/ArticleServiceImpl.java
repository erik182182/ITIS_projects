package com.sera.wellness.services;

import com.sera.wellness.forms.ArticleAddForm;
import com.sera.wellness.models.Article;
import com.sera.wellness.models.User;
import com.sera.wellness.repositories.ArticleRepository;
import com.sera.wellness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Article> getAll() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public Article getArticle(Long id) {
        Optional<Article> articleCandidate = articleRepository.findOne(id);
        if (articleCandidate.isPresent()) {
            return articleCandidate.get();
        } else {
            throw new IllegalArgumentException("Такой статьи не существует.");
        }
    }

    public void addArticle(ArticleAddForm form, User user) {
        Article article = Article.builder()
                .title(form.getTitle())
                .text(form.getText())
                .user(user)
                .build();
        articleRepository.save(article);
    }

    @Override
    public Boolean addFavoriteArticle(Long articleId, User user) {
        user.getFavoriteArticles().add(articleRepository.findOne(articleId).get());
        userRepository.update(user);
        return true;
    }
}
