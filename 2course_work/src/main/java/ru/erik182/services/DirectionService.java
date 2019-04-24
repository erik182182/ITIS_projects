package ru.erik182.services;

import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;

import java.util.Set;

public interface DirectionService {
    Set<Direction> getDirections(User user);
}
