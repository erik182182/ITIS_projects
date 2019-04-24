package ru.erik182.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.erik182.models.Subject;
import ru.erik182.repositories.mappers.SubjectRowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class SubjectRepositoryImpl implements SubjectRepository {

    private JdbcTemplate jdbcTemplate;

    private String SQL_GET_SUBJ = "SELECT * FROM subject WHERE id = ?;";

    private String SQL_SAVE_SUBJ = "INSERT INTO subj(name) VALUES(?);";

    private String SQL_DELETE_SUBJ = "DELETE FROM subj WHERE id = ?;";

    private String SQL_UPDATE_SUBJ = "UPDATE subj SET name = ? WHERE id = ?";

    private String SQL_GET_ALL_SUBJ = "SELECT * FROM subject;";

    @Autowired
    public SubjectRepositoryImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Subject> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_SUBJ, new SubjectRowMapper()));
    }

    @Override
    public void save(Subject model) {
        jdbcTemplate.update(SQL_SAVE_SUBJ, model.getName());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_SUBJ, id);
    }

    @Override
    public void update(Long id, Subject model) {
        jdbcTemplate.update(SQL_UPDATE_SUBJ, model.getName(), id);
    }

    @Override
    public List<Subject> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL_SUBJ, new SubjectRowMapper());
    }
}
