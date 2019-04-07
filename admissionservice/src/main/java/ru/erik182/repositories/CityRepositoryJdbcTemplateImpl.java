package ru.erik182.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CityRepositoryJdbcTemplateImpl implements CityRepository {

    private final static String SQL_GET_ALL_CITIES = "SELECT * FROM city;";

    private final static String SQL_SAVE_CITY = "INSERT INTO city(city) VALUES(?);";

    private final static String SQL_GET_CITY = "SELECT * FROM city WHERE id_city = ?;";

    private final static String SQL_DELETE_CITY = "DELETE FROM city WHERE id_city = ?;";

    private final static String SQL_UPDATE_CITY = "UPDATE city SET city WHERE id_city = ?;";
    private JdbcTemplate jdbcTemplate;

    private RowMapper<String> rowMapper = new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong("id_city")+" "+resultSet.getString("city");
        }
    };

    public CityRepositoryJdbcTemplateImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void save(String city){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SAVE_CITY, new String[]{"id_city"});
                    ps.setString(1, city);
                    return ps;
                }, keyHolder);
    }

    @Override
    @SneakyThrows
    public Optional<String> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_CITY, rowMapper, id));
    }


    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_CITY, id);
    }

    @Override
    public void update(Long id, String model) {
        jdbcTemplate.update(SQL_UPDATE_CITY, id);
    }

    @Override
    public List<String> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL_CITIES, rowMapper);
    }
}
