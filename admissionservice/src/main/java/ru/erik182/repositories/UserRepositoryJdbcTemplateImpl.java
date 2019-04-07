package ru.erik182.repositories;

import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.erik182.mappers.DeclarationRowMapper;
import ru.erik182.mappers.ExamRowMapper;
import ru.erik182.mappers.UserRowMapper;
import ru.erik182.models.Declaration;
import ru.erik182.models.Exam;
import ru.erik182.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserRepositoryJdbcTemplateImpl implements UserRepository {


    private JdbcTemplate jdbcTemplate;


    private static final String SQL_SELECT_USER = "SELECT * FROM \"user\" WHERE user_id = ?;";

    private static final String SQL_SELECT_USER_BY_PASSPORT = "SELECT * FROM \"user\" WHERE passport = ? ;";


    private static final String SQL_GET_ALL_EXAMS_OF_USER = "SELECT score, subject, exam_id FROM \"user\"\n" +
            "JOIN exam e on \"user\".user_id = e.id_user\n" +
            "JOIN subject s2 on e.id_subj = s2.id_subj WHERE passport = ?;";

    private final static String SQL_GET_ALL_USERS = "SELECT * FROM \"user\" ;";

    private final static String SQL_UPDATE_USER = "UPDATE \"user\" SET full_name = ?, passport = ? WHERE user_id = ?;";


    private static final String SQL_DELETE_USER = "DELETE FROM \"user\" WHERE user_id = ?;";

    private static final String SQL_DELETE_EXAMS_OF_USER = "DELETE FROM exam WHERE id_user=?;";

    private static final String SQL_DELETE_DECS_OF_USER = "DELETE FROM declaration WHERE id_user=?;";

    private final static String SQL_INSERT_USER = "INSERT INTO \"user\"(full_name, passport, password, type)\n" +
            "    VALUES (?,?,?,'user');";


    public UserRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserRowMapper(), id));
    }

    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_USER, new String[] {"user_id"});
                    ps.setString(1, model.getFullName());
                    ps.setString(2, model.getPassport());
                    ps.setString(3, model.getHashPassword());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_EXAMS_OF_USER, id);
        jdbcTemplate.update(SQL_DELETE_DECS_OF_USER, id);
        jdbcTemplate.update(SQL_DELETE_USER, id);
    }

    @Override
    public void update(Long id, User model) {
        jdbcTemplate.update(SQL_UPDATE_USER, model.getFullName(), model.getPassport(), id);
    }

    @Override
    public List<User> findAll() {
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user;
                user = new UserRowMapper().mapRow(resultSet, i);
                user.setExams(jdbcTemplate.query(SQL_GET_ALL_EXAMS_OF_USER, new ExamRowMapper(), user.getPassport()));
                return user;
            }
        };
        return jdbcTemplate.query(SQL_GET_ALL_USERS, rowMapper);
    }

    @Override
    @SneakyThrows
    public Optional<User> getUserByPassport(String passport) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_PASSPORT, new UserRowMapper(), passport));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }



}
