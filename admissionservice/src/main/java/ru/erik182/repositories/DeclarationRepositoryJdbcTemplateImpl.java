package ru.erik182.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.erik182.mappers.DeclarationRowMapper;
import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeclarationRepositoryJdbcTemplateImpl implements DeclarationRepository {

    private JdbcTemplate jdbcTemplate;


    private static final String SQL_GET_DEC = "SELECT * FROM declaration WHERE dec_id = ?;";

    private static final String SQL_SAVE_DEC = "INSERT INTO declaration(id_user, id_dir, sum_score)\n" +
            "    VALUES (?,?,?);";

    private static final String SQL_GET_DEC_OF_USER = "SELECT dec_id, declaration.id_user, full_name, d2.id_dir, d2.name, budget_places, university.name as uni_name, sum_score FROM declaration\n" +
            "JOIN \"user\" ON declaration.id_user = \"user\".user_id\n" +
            "JOIN direction d2 on declaration.id_dir = d2.id_dir\n" +
            "JOIN university ON d2.id_uni = university.id_uni\n" +
            "WHERE passport=?;";

    private static final String SQL_GET_DEC_OF_DIR = "SELECT full_name,sum_score FROM direction\n" +
            "JOIN declaration d2 on direction.id_dir = d2.id_dir\n" +
            "JOIN \"user\" ON d2.id_user = \"user\".user_id WHERE d2.id_dir = ?;";


    private static final String SQL_DELETE_DEC = "DELETE FROM declaration WHERE dec_id = ?;";

    private static final String SQL_UPDATE_DEC = "UPDATE declaration SET id_dir = ?, id_user = ?, sum_score = ?\n" +
            "WHERE dec_id = ?;";

    private static final String SQL_GET_ALL_DECS = "SELECT * FROM declaration ;";

    public DeclarationRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Optional<Declaration> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_DEC, new DeclarationRowMapper(), id));
    }

    @Override
    @SneakyThrows
    public void save(Declaration model) {
        jdbcTemplate.update(SQL_SAVE_DEC, model.getUser().getId(),
                model.getDirection().getId(), model.getSumScore());
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_DEC, id);
    }

    @Override
    @SneakyThrows
    public void update(Long id, Declaration model) {
        jdbcTemplate.update(SQL_UPDATE_DEC, model.getDirection().getId(),
                model.getUser().getId(), model.getSumScore(), id);
    }

    @Override
    @SneakyThrows
    public List<Declaration> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL_DECS, new DeclarationRowMapper());
    }


    public List<Declaration> getDeclarationsOfDirectionById(Long id){
        RowMapper rowMapper = new RowMapper<Declaration>() {
            @Override
            public Declaration mapRow(ResultSet resultSet, int i) throws SQLException {
                return Declaration.builder()
                        .sumScore(resultSet.getInt("sum_score"))
                        .user(User.builder().fullName(resultSet.getString("full_name")).build())
                        .build();
            }

        };
        return jdbcTemplate.query(SQL_GET_DEC_OF_DIR, rowMapper, id);
    }

    public List<Declaration> getDeclarationsOfUser(String passport){
        return jdbcTemplate.query(SQL_GET_DEC_OF_USER, new DeclarationRowMapper(), passport);
    }

}
