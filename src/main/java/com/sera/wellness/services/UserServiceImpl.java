package com.sera.wellness.services;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.forms.UserRegistrationForm;
import com.sera.wellness.models.User;

import com.sera.wellness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.config.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()){
            return userOptional.get();
        }else {
            return null;
            //throw new SecurityException("User with email <" + email + "> not found");
        }
    }
}
