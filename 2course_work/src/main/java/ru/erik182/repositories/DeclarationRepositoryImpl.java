package ru.erik182.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.erik182.models.Declaration;
import ru.erik182.repositories.mappers.DeclarationRowMapper;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class DeclarationRepositoryImpl implements DeclarationRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_DEC = "SELECT * FROM declaration WHERE dec_id = ?;";

    private static final String SQL_SAVE_DEC = "INSERT INTO declaration(id_user, id_dir, sum_score)\n" +
            "    VALUES (?,?,?);";

    private static final String SQL_GET_DEC_OF_USER = "SELECT dec_id, declaration.id_user, full_name, d2.id_dir, d2.name, budget_places, university.name as uni_name, sum_score FROM declaration\n" +
            "JOIN \"user\" ON declaration.id_user = \"user\".id\n" +
            "JOIN direction d2 on declaration.id_dir = d2.id\n" +
            "JOIN university ON d2.id_uni = university.id\n" +
            "WHERE \"user\".id = ?;";

    private static final String SQL_GET_DEC_OF_DIR = "SELECT full_name,sum_score FROM direction\n" +
            "JOIN declaration d2 on direction.id_dir = d2.id_dir\n" +
            "JOIN \"user\" ON d2.id_user = \"user\".user_id WHERE d2.id_dir = ?;";


    private static final String SQL_DELETE_DEC = "DELETE FROM declaration WHERE dec_id = ?;";

    private static final String SQL_UPDATE_DEC = "UPDATE declaration SET id_dir = ?, id_user = ?, sum_score = ?\n" +
            "WHERE dec_id = ?;";

    private static final String SQL_GET_ALL_DECS = "SELECT * FROM declaration ;";

    @Autowired
    public DeclarationRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Set<Declaration> findDeclarationsOfUser(Long userId) {
        return new HashSet<>(jdbcTemplate.query(SQL_GET_DEC_OF_USER, new DeclarationRowMapper(), userId));
    }

    @Override
    public Optional<Declaration> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Declaration model) {
        jdbcTemplate.update(SQL_SAVE_DEC, model.getUser().getId(),
                model.getDirection().getId(), model.getSumScore());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Long id, Declaration model) {

    }

    @Override
    public List<Declaration> findAll() {
        return null;
    }
}
