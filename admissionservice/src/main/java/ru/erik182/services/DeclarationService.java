package ru.erik182.services;

import org.json.simple.JSONObject;
import ru.erik182.dto.DeclarationDto;
import ru.erik182.dto.DirectionDto;
import ru.erik182.dto.UserDto;
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

public class DeclarationService {

    private DirectionRepository directionRepository;
    private UserRepository userRepository;
    private DeclarationRepository declarationRepository;
    private ExamRepository examRepository;

    public DeclarationService(DirectionRepository directionRepository, UserRepository userRepository,
                            DeclarationRepository declarationRepository, ExamRepository examRepository) {
        this.directionRepository = directionRepository;
        this.userRepository = userRepository;
        this.declarationRepository = declarationRepository;
        this.examRepository = examRepository;
    }


    public void saveDeclarationOfUser(String userPassport, Long dirId){
        Optional<User> userCandidate = userRepository.getUserByPassport(userPassport);
        if(userCandidate.isPresent()){
            User user = userCandidate.get();
            int sumScore = 0;
            List<Exam> exams = examRepository.getExamsOfUserByPassport(userPassport);
            if(!exams.isEmpty()){


                checkDeclaration(userPassport, dirId);


                for(Exam userExam:exams){
                    for(Exam dirExam: examRepository.getExamsOfDirectionById(dirId)){
                        if (userExam.getSubject()
                                .equals(dirExam.getSubject())) sumScore += userExam.getScore();
                    }
                }
                declarationRepository.save(Declaration.builder()
                        .direction(Direction.builder().id(dirId).build())
                        .user(user)
                        .sumScore(sumScore)
                        .build());
            }
            else throw new IllegalArgumentException("Вы не сдали ни одного экзамена.");
        }
        else throw new IllegalArgumentException("Такого пользователя не существует.");
    }

    private void checkDeclaration(String passport, long dirId){
        List<Direction> ids = directionRepository.getChosenDirectionsOfUser(passport);

        if(ids.size() >= 3) throw new IllegalArgumentException("Вы подали максимальное количество заявлений.");

        if(ids.contains(dirId)) throw new IllegalArgumentException("Вы уже подали заявление на это навправление");
    }

    public List<DeclarationDto> getDeclarationsOfUser(String passport){
        return DeclarationDto.from(declarationRepository.getDeclarationsOfUser(passport));
    }

    public List<DirectionDto> getDirectionOfUser(String passport){
        List<Direction> dirsWithoutDecs = directionRepository.getChosenDirectionsOfUser(passport);
        List<Direction> dirs = new ArrayList<>();

        for(Direction dir: dirsWithoutDecs){
            Direction direction = directionRepository.findOne(dir.getId()).get();
            List<Declaration> declarations = declarationRepository.getDeclarationsOfDirectionById(dir.getId());
            declarations.sort( (s1,s2) -> {
                return s2.getSumScore() - s1.getSumScore();
            });
            direction.setDeclarations(declarations);

            dirs.add(direction);
        }

        return DirectionDto.from(dirs);
    }

    public JSONObject generateJSON(UserDto user, Long dirId){
        JSONObject json = new JSONObject();
        try{
            this.saveDeclarationOfUser(user.getPassport(),
                    Long.valueOf(dirId) );
            json.put("message", "Заявление успешно сохранено.");
            json.put("flag", "success");
            List<String> decs = new ArrayList<>();
            for(DeclarationDto declaration:this.getDeclarationsOfUser(user.getPassport()) ){
                decs.add(declaration.getDirection().getName() );
            }
            json.put("size", decs.size());
            json.put("declarations", decs);
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            json.put("message", e.getMessage());
            json.put("flag", "danger");
        }
        return json;
    }

}
