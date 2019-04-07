package ru.erik182.repositories;

import ru.erik182.models.Declaration;
import ru.erik182.models.Exam;
import ru.erik182.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User> {
    Optional<User> getUserByPassport(String passport);
}
