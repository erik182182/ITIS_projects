package ru.erik182.repositories;

import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;

import java.util.List;
import java.util.Set;

public interface ExamRepository extends CrudRepository<Exam>{
    Set<Exam> findExamsOfUser(Long userId);
}
