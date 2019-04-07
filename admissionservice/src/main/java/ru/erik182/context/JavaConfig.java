package ru.erik182.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.erik182.models.User;
import ru.erik182.repositories.*;
import ru.erik182.services.AdminService;
import ru.erik182.services.DeclarationService;
import ru.erik182.services.DirectionService;
import ru.erik182.services.UserService;

import javax.sql.DataSource;

@Configuration
public class JavaConfig {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "rrr182";
    private static final String URL ="jdbc:postgresql://localhost:5432/postgres";

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ExamRepository examRepository() throws ClassNotFoundException {
        return new ExamRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public UserRepository userRepository() throws ClassNotFoundException {
        return new UserRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public DeclarationRepository declarationRepository() throws ClassNotFoundException {
        return new DeclarationRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public DirectionRepository directionRepository() throws ClassNotFoundException {
        return new DirectionRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public UniversityRepository universityRepository() throws ClassNotFoundException {
        return new UniversityRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public CityRepository cityRepository() throws ClassNotFoundException {
        return new CityRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public AdminService adminService() throws ClassNotFoundException {
        return new AdminService(userRepository(), universityRepository(), directionRepository(), examRepository(),cityRepository(), passwordEncoder());
    }

    @Bean
    public DeclarationService declarationService() throws ClassNotFoundException {
        return new DeclarationService(directionRepository(),userRepository(),declarationRepository(),examRepository());
    }

    @Bean
    public DirectionService directionService() throws ClassNotFoundException {
        return new DirectionService(directionRepository(), examRepository());
    }

    @Bean
    public UserService userService() throws ClassNotFoundException {
        return new UserService(userRepository(),examRepository(),passwordEncoder());
    }

}