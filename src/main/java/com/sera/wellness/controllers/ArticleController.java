package com.sera.wellness.controllers;

import com.sera.wellness.forms.ArticleAddForm;
import com.sera.wellness.forms.CommentForm;
import com.sera.wellness.models.Article;
import com.sera.wellness.models.Comment;
import com.sera.wellness.models.User;
import com.sera.wellness.security.UserDetailsImpl;
import com.sera.wellness.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {
   /* @Autowired
    private HttpSession session;*/
    @Autowired
    private ArticleService service;
    @Autowired
    private ApplicationContext context;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap modelMap, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
//        System.out.println(user);
        modelMap.addAttribute("articles",service.getAll());
        return "articles";
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public String getArticle(@PathVariable Long id, ModelMap modelMap,Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        try{
            Article article = service.getArticle(id);
            modelMap.addAttribute("usersGrade",service.getUsersGrade(user.getId(),id));
            modelMap.addAttribute("article", article);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "article";
    }

    @RequestMapping(path = "add", method = RequestMethod.GET)
    public String getArticleForm(ModelMap modelMap){
        return "addArticle";
    }

    @RequestMapping(path = "add",method = RequestMethod.POST)
    public String addArticle(@RequestParam("title") String title,
                             @RequestParam("text") String text,
                              ModelMap modelMap,
                             Authentication authentication) {
        ArticleAddForm form = ArticleAddForm.builder()
                        .title(title)
                        .text(text)
                        .build();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        service.addArticle(form, user);
        return "redirect:/articles";
    }

    @GetMapping("/favorite")
    public String getFavoriteArticles(ModelMap modelMap, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
//        System.out.println((user.getId()));
        modelMap.addAttribute("articles", user.getFavoriteArticles());
        return "favoriteArticles";
    }

    @PostMapping("/{id}/addfavorite")
    public String addFavorite(@PathVariable("id") Long articleId,
            ModelMap modelMap, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        service.addFavoriteArticle(articleId, user);
        return "redirect:/articles/favorite";
    }
    @PostMapping("/addComment")
    public String addComment(@ModelAttribute CommentForm commentForm, ModelMap modelMap, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        service.addComment(commentForm,user.getId());
        return "redirect:/articles/"+commentForm.getArticleId();
    }
    @PostMapping("/{id}/evaluate")
    public String evaluate(@RequestParam Short grade ,@PathVariable("id") Long articleId ,Authentication authentication ,ModelMap modelMap) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        service.evaluate(user.getId(),articleId,grade);
        return "redirect:/articles/"+articleId;
    }


}
