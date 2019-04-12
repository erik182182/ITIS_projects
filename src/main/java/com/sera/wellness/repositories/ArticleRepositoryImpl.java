package com.sera.wellness.repositories;

import com.sera.wellness.models.Article;
import com.sera.wellness.repositories.mappers.ArticleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private JdbcTemplate jdbcTemplate;
    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO " +
            " \"articles\"(title, text, resources, id_user) " +
            " VALUES(?,?,?,?);";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM \"articles\" " +
            "WHERE id = ?;";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE \"articles\" " +
            "SET title = ?, text = ?, resources = ?, id_user = ? WHERE id = ?;";
    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM \"articles\" " +
            "WHERE id = ?;";
    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM \"articles\";";

    @Autowired
    public ArticleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Article model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[]{"id"});

                    ps.setString(1, model.getTitle());
                    ps.setString(2, model.getText());
                    ps.setArray(3, connection.createArrayOf("text", model.getResources()));
                    ps.setLong(4, model.getUser().getId());
                    return ps;
                }, keyHolder);


        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public void update(Long id, Article model) {
        jdbcTemplate.update(SQL_UPDATE, model.getTitle(), model.getText(),
                model.getResources(), model.getUser().getId(), model.getId());
    }

    @Override
    public Optional<Article> findOne(Long id) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new ArticleRowMapper(), id));
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new ArticleRowMapper());
    }
}
