package com.sera.wellness.controllers;

import com.sera.wellness.models.Article;
import com.sera.wellness.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService service;
    @Autowired
    private ApplicationContext context;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("articles",service.getAll());

        return "articles";
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public String getArticle(@PathVariable Long id, ModelMap modelMap) {
        Optional<Article> articleCandidat = service.getOne(id);
        articleCandidat.ifPresent(article -> modelMap.addAttribute("article", article));
        if (!articleCandidat.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return "article";
    }

    @RequestMapping(path = "add",method = RequestMethod.POST)
    public String addArticle(@ModelAttribute Article article, ModelMap modelMap) {
        service.addArticle(article);
        return "redirect:" + context.getApplicationName() + "/articles";
    }

}
