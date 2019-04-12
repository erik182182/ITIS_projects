package com.sera.wellness.repositories;

import com.sera.wellness.models.User;
import com.sera.wellness.repositories.mappers.UserRowMapper;
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


public class UserRepositoryImpl  implements UserRepository{

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_USER = "INSERT INTO " +
            " \"user\"(email, hash_password, first_name, last_name, age, weight, growth, purpose_weight," +
            " sex, consent_to_receive_emails)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?);";

    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM \"user\" " +
            "WHERE email = ?;";

    private static final String SQL_DELETE_USER = "DELETE FROM \"user\" " +
            "WHERE id = ?;";

    private static final String SQL_UPDATE_USER = "UPDATE \"user\" " +
            "SET first_name = ?, last_name = ?, email = ?, hash_password = ?," +
            "age = ?, weight = ?, growth = ?, purpose_weight = ?, sex = ?, consent_to_receive_emails = ? WHERE id = ?;";

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM \"user\" " +
            "WHERE id = ?;";

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM \"user\";";
    @Autowired
    public UserRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_USER, new String[] {"id"});
                    ps.setString(1,model.getEmail());
                    ps.setString(2,model.getHashPassword());
                    ps.setString(3,model.getFirstName());
                    ps.setString(4,model.getLastName());
                    ps.setInt(5,model.getAge());
                    ps.setInt(6,model.getWeight());
                    ps.setInt(7,model.getGrowth());
                    ps.setInt(8,model.getPurposeWeight());
                    ps.setByte(9,model.getSex());
                    ps.setBoolean(10,model.getConsentToReceiveEmails());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER, id);
    }

    @Override
    public void update(Long id, User model) {
        jdbcTemplate.update(SQL_UPDATE_USER, model.getFirstName(), model.getLastName(),  model.getEmail(),
                model.getHashPassword(),model.getAge(),model.getWeight(),model.getGrowth(),model.getPurposeWeight(),
                model.getSex(), model.getConsentToReceiveEmails(), model.getId());
    }

    @Override
    public Optional<User> findOne(Long id) {
        try{
            return Optional.of((User)jdbcTemplate.query(SQL_FIND_USER_BY_ID, new UserRowMapper(), id));
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }



    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_USERS, new UserRowMapper());
    }

    @Override
    public Optional<User> findByEmail(String name) {
        try{
            return Optional.of(
                    (User)jdbcTemplate.query(SQL_FIND_USER_BY_EMAIL, new UserRowMapper()));
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
