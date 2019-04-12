package com.sera.wellness.services;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.forms.UserRegistrationForm;
import com.sera.wellness.models.User;
import com.sera.wellness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void signUp(UserRegistrationForm form) {
        if(!form.getConsentToTheProcessingOfPersonalData().equals("true"))
            throw new IllegalArgumentException("Согласие обязательно.");
        if(!form.getPassword().equals(form.getRepeatPassword()))
            throw new IllegalArgumentException("Пароли не совпадают.");
        byte sexToByte = (byte) (form.getSex().equals("Male")?1:
                form.getSex().equals("Female")?-1:0);
        if(userRepository.findByEmail(form.getEmail()).isPresent())
            throw new IllegalArgumentException("Такой пользователь уже зарегистрирован");
        if(sexToByte == 0) throw new IllegalArgumentException("Такого пола не существует");
        userRepository.save(User.builder()
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .consentToReceiveEmails(form.getConsentToReceiveEmails().
                        equals("true"))
                .sex(sexToByte)
                //.hashPassword( new BCryptPasswordEncoder().encode(form.getPassword()))
                .hashPassword(form.getPassword())//
                .build());
    }

    @Override
    public void signIn(UserLoginForm form) {
        Optional<User> userCanidate = userRepository.findByEmail(form.getEmail());
        if(userCanidate.isPresent()){
//            if(!userCanidate.get().getHashPassword().equals(
//                    new BCryptPasswordEncoder().encode(form.getPassword()))){
//                throw new IllegalArgumentException("Неверный пароль");
//            }
        }
        else throw new IllegalArgumentException("Пользователя с таким email не существует.");
    }

    @Override
    public boolean checkCookie(User user, String cookieValue) {
        return true;
    }


}
