package ru.erik182.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.erik182.mappers.DirectionExamRowMapper;
import ru.erik182.mappers.ExamRowMapper;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExamRepositoryJdbcTemplateImpl implements ExamRepository {


    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_EXAMS_OF_DIR = "SELECT subject, min_score FROM direction\n" +
            "JOIN score_info s2 on direction.id_dir = s2.id_dir\n" +
            "JOIN subject s3 on s2.id_subj = s3.id_subj WHERE direction.id_dir = ?";

    private static final String SQL_GET_ALL_EXAMS_OF_USER = "SELECT score, subject, exam_id FROM \"user\"\n" +
            "JOIN exam e on \"user\".user_id = e.id_user\n" +
            "JOIN subject s2 on e.id_subj = s2.id_subj WHERE passport = ?;";

    private final static String SQL_GET_SUBJ_BY_ID = "SELECT * FROM subject WHERE id_subj=?;";

    private final static String SQL_GET_SUBJ_BY_SUBJ = "SELECT * FROM subject WHERE subject=?;";

    private final static String SQL_GET_EXAM = "SELECT * FROM exam WHERE exam_id = ?;";

    private final static String SQL_SAVE_EXAM = "INSERT INTO exam(id_user, id_subj, score)\n" +
            "    VALUES (?,?);";

    private final static String SQL_DELETE_EXAM = "DELETE FROM university WHERE id_uni = ?;";

    private final static String SQL_UPDATE_EXAM = "UPDATE exam SET id_user = ?, score = ? WHERE exam_id = ?;";

    private final static String SQL_GET_ALL_EXAMS = "SELECT * FROM exam;";

    private static final String SQL_INSERT_EXAMS_OF_DIR = "INSERT INTO score_info(id_dir, id_subj, min_score)" +
            " VALUES (?,?,?);";

    private final static String SQL_INSERT_EX_OF_USER = "INSERT INTO exam(id_user, id_subj, score) VALUES (?,?,?);";


    public ExamRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @SneakyThrows
    public Optional<Exam> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_EXAM, new ExamRowMapper(), id) );
    }

    public String getSubjectById(Long id){
        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("subject");
            }
        };
        return jdbcTemplate.queryForObject(SQL_GET_SUBJ_BY_ID, rowMapper, id);
    }

    public Long getIdSubjectBySubject(String subject){
        RowMapper<Long> rowMapper = new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getLong("id_subj");
            }
        };
        return jdbcTemplate.queryForObject(SQL_GET_SUBJ_BY_SUBJ, rowMapper, subject);
    }

    @Override
    public void saveExamsOfDirection(Direction dir) {
        for(Exam exam: dir.getExamsWithMinScore()){
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps =
                                connection.prepareStatement(SQL_INSERT_EXAMS_OF_DIR);
                        ps.setLong(1, dir.getId());
                        ps.setLong(2,this.getIdSubjectBySubject(exam.getSubject()));
                        ps.setInt(3, exam.getScore());
                        return ps;
                    });
        }
    }

    @Override
    public List<Exam> getExamsOfDirectionById(Long id) {

        return jdbcTemplate.query(SQL_GET_EXAMS_OF_DIR, new DirectionExamRowMapper(), id);

    }

    @Override
    public void saveExamsOfUser(User user) {
        for(Exam exam: user.getExams()){
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps =
                                connection.prepareStatement(SQL_INSERT_EX_OF_USER);
                        ps.setLong(1, user.getId());
                        ps.setLong(2,this.getIdSubjectBySubject(exam.getSubject()));
                        ps.setInt(3, exam.getScore());
                        return ps;
                    });
        }
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
        jdbcTemplate.update(SQL_UPDATE_EXAM, model.getUser().getId(), model.getScore(), id);
    }

    public List<Exam> getExamsOfUserByPassport(String passport){
        return jdbcTemplate.query(SQL_GET_ALL_EXAMS_OF_USER, new ExamRowMapper(), passport);
    }

    @Override
    public List<Exam> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL_EXAMS, new ExamRowMapper());
    }
}
