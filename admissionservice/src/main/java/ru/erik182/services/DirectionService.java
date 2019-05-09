package ru.erik182.services;

import ru.erik182.dto.DirectionDto;
import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;
import ru.erik182.repositories.DeclarationRepository;
import ru.erik182.repositories.DirectionRepository;
import ru.erik182.repositories.ExamRepository;
import ru.erik182.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DirectionService {

    private DirectionRepository directionRepository;
    private ExamRepository examRepository;

    public DirectionService(DirectionRepository directionRepository, ExamRepository examRepository) {
        this.directionRepository = directionRepository;
        this.examRepository = examRepository;
    }


    public List<DirectionDto> getAllDirections() {
        return DirectionDto.from(directionRepository.findAll());
    }

    public List<DirectionDto> getDirectionsForUser(String passport){
        List<Exam> userExams = examRepository.getExamsOfUserByPassport(passport);


        List<DirectionDto> directions = getAllDirections();

        List<DirectionDto> userDirections = new ArrayList<>();

        int matches = 0;
        for(DirectionDto direction: directions){
            List<Exam> dirExams = direction.getExamsWithMinScore();
            for(Exam userExam: userExams){
                for(Exam dirExam:dirExams){
                    if(userExam.getSubject().equals(dirExam.getSubject())
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
