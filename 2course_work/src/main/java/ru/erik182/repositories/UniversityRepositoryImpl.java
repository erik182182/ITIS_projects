package ru.erik182.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.erik182.models.University;
import ru.erik182.repositories.mappers.UniversityRowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UniversityRepositoryImpl implements UniversityRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_UNI = "SELECT * FROM university u\n " +
            "JOIN city c on u.id_city = c.id where u.id = ?;";

    private static final String SQL_GET_ALL_UNI = "SELECT * FROM university u JOIN city c on u.id_city = c.id_city";

    private static final String SQL_SAVE_UNI = "\n" +
            "INSERT INTO university(name, id_city, info) \n" +
            "    VALUES (?,?,?);";

    private static final String SQL_UPDATE_UNI = "UPDATE university SET name = ?, info = ?, id_city = ? WHERE id = ?;";

    private static final String SQL_DELETE_UNI = "DELETE FROM university WHERE id = ?;";

    @Autowired
    public UniversityRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<University> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_UNI, new UniversityRowMapper()));
    }

    @Override
    public void save(University model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SAVE_UNI, new String[] {"id"});
                    ps.setString(1, model.getName());
                    ps.setLong(2, model.getCity().getId());
                    ps.setString(3, model.getInfo());
                    return ps;
                }, keyHolder);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_UNI, id);
    }

    @Override
    public void update(Long id, University model) {
        jdbcTemplate.update(SQL_UPDATE_UNI, model.getName(), model.getInfo(), model.getCity().getId(), id);
    }

    @Override
    public List<University> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL_UNI, new UniversityRowMapper());
    }

}
