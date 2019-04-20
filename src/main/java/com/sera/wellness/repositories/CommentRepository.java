package com.sera.wellness.repositories;

import com.sera.wellness.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository {
//    @Query("SELECT c FROM Comment c WHERE c.article = :articleId")
//    public List<Comment> findAllByArticle(@Param("articleId") Long articleId);

    public Comment findByArticleIdAndUserId(Long articleId,Long userId);

}
