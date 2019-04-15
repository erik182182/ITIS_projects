package com.sera.wellness.services;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.forms.UserRegistrationForm;
import com.sera.wellness.models.User;
import com.sera.wellness.models.UserAuth;
import com.sera.wellness.repositories.UserAuthRepository;
import com.sera.wellness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public void signUp(UserRegistrationForm form) {
        if(!form.isConsentToTheProcessingOfPersonalData())
            throw new IllegalArgumentException("Согласие обязательно.");
        if(!form.getPassword().equals(form.getRepeatPassword()))
            throw new IllegalArgumentException("Пароли не совпадают.");
        if(userRepository.findByEmail(form.getEmail()).isPresent())
            throw new IllegalArgumentException("Такой пользователь уже зарегистрирован");
        if(!form.getSex().equals("male") &&
                !form.getSex().equals("female")) throw new IllegalArgumentException("Такого пола не существует");
        userRepository.save(User.builder()
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .consentToReceiveEmails(form.isConsentToReceiveEmails())
                .sex(form.getSex().equals("male")?true:false)
                .hashPassword( new BCryptPasswordEncoder().encode(form.getPassword()))
                .build());
    }

    @Override
    public UserAuth signIn(UserLoginForm form) {
        Optional<User> userCanidate = userRepository.findByEmail(form.getEmail());
        if(userCanidate.isPresent()){
            if(!new BCryptPasswordEncoder().matches(form.getPassword(), userCanidate.get().getHashPassword())){
                throw new IllegalArgumentException("Неверный пароль");
            } else {
                String cookieValue = UUID.randomUUID().toString();
                UserAuth userAuth = UserAuth.builder().user(userCanidate.get()).cookieValue(cookieValue).build();
                userAuthRepository.save(userAuth);

                return userAuth;
            }
        }
        else throw new IllegalArgumentException("Пользователя с таким email не существует.");
    }
    @Override
    public UserAuth auth(Long id) {
        Optional<UserAuth> candidat = userAuthRepository.findOne(id);
        return candidat.orElse(null);
    }



}
