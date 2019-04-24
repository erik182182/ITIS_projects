package ru.erik182.services;

import ru.erik182.models.Exam;
import ru.erik182.models.User;

import java.util.Set;

public interface ExamService {
    Set<Exam> getExamsOfUser(User user);
}
