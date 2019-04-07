package ru.erik182.repositories;

import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;

import java.util.List;

public interface ExamRepository extends CrudRepository<Exam>{
    String getSubjectById(Long id);
    Long getIdSubjectBySubject(String subject);
    void saveExamsOfDirection(Direction dir);
    List<Exam> getExamsOfDirectionById(Long id);
    void saveExamsOfUser(User user);
    List<Exam> getExamsOfUserByPassport(String passport);
}
