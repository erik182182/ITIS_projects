package ru.erik182.repositories;

import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;

import java.util.List;

public interface DirectionRepository extends CrudRepository<Direction> {
    List<Direction> getChosenDirectionsOfUser(String passport);
    List<Direction> getDirectionsForUser(User user);
}
