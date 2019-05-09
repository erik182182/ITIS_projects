package ru.erik182.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;
import ru.erik182.repositories.DeclarationRepository;
import ru.erik182.repositories.DirectionRepository;
import ru.erik182.repositories.ExamRepository;

import java.util.*;

@Service
public class DirectionServiceImpl implements DirectionService {

    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private  ExamRepository examRepository;

    @Override
    public Set<Direction> getDirections(User user) {
        if(user == null) return new HashSet<>(directionRepository.findAll());
        else {
            Set<Exam> userExams = examRepository.findExamsOfUser(user.getId());
            Set<Direction> directions = new HashSet<>(directionRepository.findAll());
            Set<Direction> userDirections = new HashSet<>();
            int matches = 0;
            for(Direction direction: directions){
                Set<Exam> dirExams = direction.getExamsWithMinScore();
                for(Exam userExam: userExams){
                    for(Exam dirExam:dirExams){
                        if(userExam.getSubject().getName().equals(dirExam.getSubject().getName())
                                && userExam.getScore()>= dirExam.getScore()){
                            matches++;
                            break;
                        }
                    }
                }
                if (matches==dirExams.size()) userDirections.add(direction);
                matches = 0;
            }
            return userDirections;
        }
    }

    @Override
    public Set<Direction> getDirectionsOfUserWithDeclarations(User user) {
        return directionRepository.getAppropriateDirectionsForUser(user.getId());
    }


}
