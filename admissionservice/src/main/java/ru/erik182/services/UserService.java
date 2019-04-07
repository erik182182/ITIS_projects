package ru.erik182.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.erik182.dto.ExamDto;
import ru.erik182.dto.UserDto;
import ru.erik182.forms.LoginForm;
import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.User;
import ru.erik182.repositories.ExamRepository;
import ru.erik182.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService{

    private UserRepository usersRepository;
    private ExamRepository examRepository;
    private PasswordEncoder encoder;

    public UserService(UserRepository usersRepository, ExamRepository examRepository, PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.examRepository = examRepository;
        this.encoder = encoder;
    }

    public void signIn(LoginForm loginForm){
        Optional<User> userCandidate = usersRepository.getUserByPassport(loginForm.getPassport());
        if(userCandidate.isPresent()){
            User user = userCandidate.get();
            if(!encoder.matches(loginForm.getPassword(), user.getHashPassword())){
                throw new IllegalArgumentException("Неверные данные.");
            }
        }
        else{
            throw new IllegalArgumentException("Такого абитуриента не существует.");
        }
    }


    public List<ExamDto> getExamsOfUserByPassport(String passport){
         return ExamDto.from(examRepository.getExamsOfUserByPassport(passport));
    }

    public UserDto getUserByPassport(String passport){
        return UserDto.from(usersRepository.getUserByPassport(passport).get());
    }


}
