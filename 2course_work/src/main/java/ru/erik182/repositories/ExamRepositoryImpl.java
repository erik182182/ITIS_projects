package ru.erik182.repositories;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;
import ru.erik182.repositories.mappers.ExamRowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ExamRepositoryImpl implements ExamRepository{

    private JdbcTemplate jdbcTemplate;

    private final static String SQL_GET_EXAM = "SELECT * FROM exam JOIN subject " +
            " ON exam.id_subj = subject.id WHERE id = ?;";

    private final static String SQL_SAVE_EXAM = "INSERT INTO exam(id_user, id_subj, score)\n" +
            "    VALUES (?,?);";

    private final static String SQL_DELETE_EXAM = "DELETE FROM exam WHERE id = ?;";

    private final static String SQL_UPDATE_EXAM = "UPDATE exam SET id_user = ?, score = ?, id_subj = ? WHERE id = ?;";

    private final static String SQL_GET_ALL_EXAMS = "SELECT * FROM exam JOIN subject " +
            " ON exam.id_subj = subject.id;";

    private final static String SQL_GET_EXAMS_OF_USER = "SELECT * FROM exam\n" +
            "JOIN subject s2 on exam.id_subj = s2.id\n" +
            "JOIN \"user\" u on exam.id_user = u.id WHERE u.id = ?;";

    @Autowired
    public ExamRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Exam> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_EXAM, new ExamRowMapper(), id) );
    }

    @Override
    public void save(Exam model) {
        jdbcTemplate.update(SQL_SAVE_EXAM, model.getUser().getId(), model.getScore());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_EXAM, id);
    }

    @Override
    public void update(Long id, Exam model) {
        jdbcTemplate.update(SQL_UPDATE_EXAM, model.getUser().getId(), model.getScore(), model.getSubject().getId(), id);
    }

    @Override
    public List<Exam> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL_EXAMS, new ExamRowMapper());
    }

    @Override
    public Set<Exam> findExamsOfUser(Long userId) {
        return new HashSet<>(jdbcTemplate.query(SQL_GET_EXAMS_OF_USER, new ExamRowMapper(), userId));
    }
}
