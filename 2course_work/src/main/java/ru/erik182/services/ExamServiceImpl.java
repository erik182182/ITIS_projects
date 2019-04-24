package ru.erik182.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.erik182.models.Exam;
import ru.erik182.models.User;
import ru.erik182.repositories.ExamRepository;

import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Set<Exam> getExamsOfUser(User user) {
        if(user == null) return null;
        return examRepository.findExamsOfUser(user.getId());
    }

}
