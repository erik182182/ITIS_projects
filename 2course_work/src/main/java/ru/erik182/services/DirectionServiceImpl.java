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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class DirectionServiceImpl implements DirectionService {

    @Autowired
    private DirectionRepository directionRepository;

    @Override
    public Set<Direction> getDirections(User user) {
        if(user == null) return new HashSet<>(directionRepository.findAll());
        else return directionRepository.getAppropriateDirectionsForUser(user.getId());
    }

}
