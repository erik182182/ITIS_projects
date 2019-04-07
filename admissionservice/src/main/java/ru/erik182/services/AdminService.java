package ru.erik182.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.erik182.dto.DirectionDto;
import ru.erik182.dto.UniversityDto;
import ru.erik182.dto.UserDto;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.University;
import ru.erik182.models.User;
import ru.erik182.repositories.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminService {
    private UserRepository usersRepository;
    private UniversityRepository universityRepository;
    private DirectionRepository directionRepository;
    private ExamRepository examRepository;
    private CityRepository cityRepository;
    private PasswordEncoder encoder;

    public AdminService(UserRepository usersRepository, UniversityRepository universityRepository, DirectionRepository directionRepository, ExamRepository examRepository, CityRepository cityRepository, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.usersRepository = usersRepository;
        this.universityRepository = universityRepository;
        this.directionRepository = directionRepository;
        this.examRepository = examRepository;
        this.cityRepository = cityRepository;
    }

    public List<UserDto> getAllUsers(){
        List<User> users =  usersRepository.findAll();
        List<User> res = new ArrayList<>();

        for(User user:users){
            if(user.getType().equals("user")) res.add(user);
        }

        return UserDto.from(res);
    }

    public List<String> getAllCities(){
        return cityRepository.findAll();
    }

    public List<UniversityDto> getAllUniversities(){

        return UniversityDto.from(universityRepository.findAll());
    }

    public List<DirectionDto> getAllDirections(){
         return DirectionDto.from(directionRepository.findAll());
    }




    public void saveUser(HttpServletRequest req){
        User user = User.builder()
                .fullName(req.getParameter("name"))
                .passport(req.getParameter("passport"))
                .hashPassword(encoder.encode(req.getParameter("password")) )
                .build();
        List<Exam> exams = new ArrayList<>();
        for(long i = 1; i<=9; i++){
            if(!req.getParameter("subj-"+i).equals("")){
                exams.add(Exam.builder()
                        .score(Integer.valueOf(req.getParameter("subj-"+i)))
                        .subject(examRepository.getSubjectById(i))
                        .build());

            }
        }
        user.setExams(exams);
        System.out.println(user);
        try{
            usersRepository.save(user);
            examRepository.saveExamsOfUser(user);
            req.setAttribute("message", "Успешно сохранено.");
        }
        catch(IllegalArgumentException e){
            req.setAttribute("message", e.getMessage());
        }
    }

    public void deleteUser(HttpServletRequest req){
        Optional<User> userCandidate = usersRepository.getUserByPassport(req.getParameter("passport"));
        if(userCandidate.isPresent()){
            usersRepository.delete(userCandidate.get().getId());
        } else{
            throw new IllegalArgumentException("Пользователя с таким ID не существует.");
        }
    }

    public void saveCity(HttpServletRequest req) {
        cityRepository.save(req.getParameter("city"));
    }

    public void saveUniversity(HttpServletRequest req){
        University university = University.builder()
                .city(req.getParameter("city"))
                .name(req.getParameter("uni"))
                .info(req.getParameter("info"))
                .build();

        universityRepository.save(university);
    }

    public void saveDirection(HttpServletRequest req){
        Direction direction = Direction.builder()
                .name(req.getParameter("dir"))
                .university(University.builder().id(Long.valueOf(req.getParameter("uni"))).build())
                .info(req.getParameter("info"))
                .budgetPlaces(Integer.valueOf(req.getParameter("budget")))
                .build();
        List<Exam> exams = new ArrayList<>();
        for(long i = 1; i<=9; i++){
            if(!req.getParameter("subj-"+i).equals("")){
                exams.add(Exam.builder()
                        .score(Integer.valueOf(req.getParameter("subj-"+i)))
                        .subject(examRepository.getSubjectById(i))
                        .build());

            }
        }

        direction.setExamsWithMinScore(exams);

        directionRepository.save(direction);
        examRepository.saveExamsOfDirection(direction);
    }
}
