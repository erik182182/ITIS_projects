package com.sera.wellness.repositories.mappers;

import com.sera.wellness.models.Article;
import com.sera.wellness.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet resultSet, int i) throws SQLException {
        return Article.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .text(resultSet.getString("text"))
                .resources((String[])resultSet.getArray("resources").getArray())
                .user(User.builder().id(resultSet.getLong("id_user")).build())
                .build();
    }
}
