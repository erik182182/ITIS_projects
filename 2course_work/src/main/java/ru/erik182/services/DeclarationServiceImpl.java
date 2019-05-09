package ru.erik182.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;
import ru.erik182.repositories.DeclarationRepository;
import ru.erik182.repositories.DirectionRepository;
import ru.erik182.repositories.UserRepository;

import java.util.IllformedLocaleException;
import java.util.Set;

@Service
public class DeclarationServiceImpl implements DeclarationService {

    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Override
    public Set<Declaration> getDeclarationsOfUser(User user) {
        if(user == null) return null;
        return declarationRepository.findDeclarationsOfUser(user.getId());
    }

    @Override
    public void saveDeclarationOfUser(User user, Direction direction) {
        if(user == null) throw new IllegalArgumentException("Необходима авторизация.");
        if(!declarationRepository.findOne(direction.getId()).isPresent())
            throw new IllegalArgumentException("Данного направления не существует.");
        Integer sumScore = 0;
        for(Exam exam:user.getExams()){
            sumScore += exam.getScore();
        }
        Declaration declaration = Declaration.builder()
                .user(user)
                .direction(direction)
                .sumScore(sumScore)
                .build();
        declarationRepository.save(declaration);
    }


}
