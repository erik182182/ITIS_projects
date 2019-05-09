package ru.erik182.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.erik182.models.*;
import ru.erik182.repositories.mappers.DirectionRowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class DirectionRepositoryImpl implements DirectionRepository{

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL_DIR = "SELECT d.id, d.info, budget_places, d.name, u.id as uni_id, u.info as uni_info,\n" +
            "u.name as uni_name, c.id as city_id, c.name as city_name,\n" +
            "  s.id as subj_id, s.name as subj_name, min_score FROM direction d\n" +
            "JOIN university u on d.id_uni = u.id\n" +
            "JOIN city c on u.id_city = c.id\n" +
            "JOIN score_info s2 on d.id = s2.id_dir\n" +
            "JOIN subject s on s2.id_subj = s.id;";

    private static final String SQL_GET_DIR = "SELECT * FROM direction d \n" +
            "JOIN university u on d.id_uni = u.id\n" +
            "JOIN city c on u.id_city = c.id\n" +
            "JOIN score_info s2 on d.id = s2.id_dir\n" +
            "JOIN subject s on s2.id_subj = s.id\n" +
            "JOIN exam e on s.id = e.id_subj\n" +
            "WHERE id = ?;";

    private static final String SQL_INSERT_DIR = "INSERT INTO direction(name, id_uni, info, budget_places) \n" +
            "    VALUES (?,?,?,?);";

    private static final String SQL_DELETE_DIR = "DELETE FROM direction WHERE id = ?;";

    private static final String SQL_UPDATE_DIR = "UPDATE direction SET name = ?, id_uni = ?, budget_places = ?, info = ?\n" +
            "WHERE id = ?;";

    @Autowired
    public DirectionRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Direction> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_DIR, new DirectionRowMapper(), id));
    }

    @Override
    public void save(Direction model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_DIR, new String[] {"id"});
                    ps.setString(1, model.getName());
                    ps.setLong(2, model.getUniversity().getId());
                    ps.setString(3, model.getInfo());
                    ps.setInt(4,model.getBudgetPlaces());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_DIR, id);
    }

    @Override
    public void update(Long id, Direction model) {
        jdbcTemplate.update(SQL_UPDATE_DIR, model.getName(), model.getUniversity().getId(),
                model.getBudgetPlaces(), model.getInfo(), id);
    }

    @Override
    public List<Direction> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL_DIR, new ResultSetExtractor<List<Direction>>() {
            @Override
            public List<Direction> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                List<Direction> dirs = new ArrayList<>();
                do{
                    Direction direction = Direction.builder()
                            .id(resultSet.getLong("id"))
                            .info(resultSet.getString("info"))
                            .budgetPlaces(resultSet.getInt("budget_places"))
                            .name(resultSet.getString("name"))
                            .university(University.builder()
                                    .id(resultSet.getLong("uni_id"))
                                    .info(resultSet.getString("uni_info"))
                                    .name(resultSet.getString("uni_name"))
                                    .city(City.builder()
                                            .id(resultSet.getLong("city_id"))
                                            .name(resultSet.getString("city_name"))
                                            .build())
                                    .build())
                            .build();
                    long id = resultSet.getLong("id");
                    Set<Exam> examsOfDirecion = new HashSet<>();
                    do {
                        examsOfDirecion.add(Exam.builder()
                                .score(resultSet.getInt("min_score"))
                                .subject(Subject.builder()
                                        .id(resultSet.getLong("subj_id"))
                                        .name(resultSet.getString("subj_name")).build())
                                .build());
                    }
                    while ( resultSet.next() && resultSet.getLong("id") == id );
                    direction.setExamsWithMinScore(examsOfDirecion);
                    dirs.add(direction);
                } while (!resultSet.isAfterLast());
                return dirs;
            }
        });
    }

    @Override
    public Set<Direction> getAppropriateDirectionsForUser(Long userId) {
        //TODO: SQL
         return null;
    }
}
