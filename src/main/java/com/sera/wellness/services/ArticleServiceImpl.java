package com.sera.wellness.services;

import com.sera.wellness.forms.ArticleAddForm;
import com.sera.wellness.forms.CommentForm;
import com.sera.wellness.models.Article;
import com.sera.wellness.models.Comment;
import com.sera.wellness.models.UploadedFile;
import com.sera.wellness.models.User;
import com.sera.wellness.repositories.ArticleRepository;
import com.sera.wellness.repositories.CommentRepository;
import com.sera.wellness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ServletContext servletContext;

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
        if(form.getFile().isEmpty()) {
            throw new IllegalArgumentException("empty file");
        }
        //System.out.println(form.getFile().getOriginalFilename());
        String[] tmp = form.getFile().getOriginalFilename().split("\\.");
        String type = tmp[tmp.length-1];
        String fileName = "uploads/" + StuffService.generateUniqueFileNameForUsersUploads("imgarticles",user.getId())
                                                                       + "." +type;
        File file = new File("C:/server/uploads/" + fileName);
        System.out.println(file.getAbsolutePath().toString());
        try {
            if (file.createNewFile()) {
                Files.write(file.toPath(), form.getFile().getBytes());
                article.setMainImg(UploadedFile.builder().fileName(fileName).build());
            } else {
                throw new IllegalArgumentException("error of creating new unique file name");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        articleRepository.save(article);
    }

    @Override
    public Boolean addFavoriteArticle(Long articleId, User user) {
        if (user.getFavoriteArticles().contains(Article.builder().id(articleId).build())){
            return false;
        }
        user.getFavoriteArticles().add(articleRepository.findOne(articleId).get());
        userRepository.update(user);
        return true;
    }

    @Override
    public void addComment(CommentForm commentForm, Long userId) {
        commentRepository.save(Comment.builder()
                .article(Article.builder().id(commentForm.getArticleId()).build())
                .text(commentForm.getText())
                .user(User.builder().id(userId).build())
                .build());
    }
    @Override
    public void evaluate(Long userId,Long articleId,Short grade) {
        articleRepository.evaluate(userId,articleId,grade);
    }
    @Override
    public Short getUsersGrade(Long userId,Long articleId) {
        return articleRepository.getUsersGrade(userId,articleId);
    }
}
