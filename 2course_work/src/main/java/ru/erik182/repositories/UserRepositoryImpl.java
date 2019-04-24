package ru.erik182.repositories;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.erik182.models.User;
import ru.erik182.repositories.mappers.UserRowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_USER = "SELECT * FROM \"user\" WHERE id = ?;";

    private static final String SQL_SELECT_USER_BY_PASSPORT = "SELECT * FROM \"user\" WHERE passport = ? ;";

    private final static String SQL_GET_ALL_USERS = "SELECT * FROM \"user\" ;";

    private final static String SQL_UPDATE_USER = "UPDATE \"user\" SET full_name = ?, passport = ? WHERE id = ?;";

    private static final String SQL_DELETE_USER = "DELETE FROM \"user\" WHERE id = ?;";

    private static final String SQL_DELETE_EXAMS_OF_USER = "DELETE FROM exam WHERE id_user=?;";

    private static final String SQL_DELETE_DECS_OF_USER = "DELETE FROM declaration WHERE id_user=?;";

    private final static String SQL_INSERT_USER = "INSERT INTO \"user\"(full_name, passport, password)\n" +
            "    VALUES (?,?,?);";

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
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
                            connection.prepareStatement(SQL_INSERT_USER, new String[] {"id"});
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
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserRowMapper());
    }

    @Override
    public Optional<User> findByPassport(String passport) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_PASSPORT, new UserRowMapper(), passport));
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

}
