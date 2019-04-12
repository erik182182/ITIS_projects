package com.sera.wellness.services;

import com.sera.wellness.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    public List<Article> getAll();
    public Optional<Article> getOne(Long id);
    public void addArticle(Article article);
}
