package ru.erik182.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.erik182.models.User;
import ru.erik182.models.forms.SignInForm;
import ru.erik182.repositories.UserRepository;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User singIn(SignInForm form) {
        if(!Pattern.matches("^[0-9]{10}$", form.getPassport()))
            throw new IllegalArgumentException("Паспорт введен неверно.");
        Optional<User> userCandidate = userRepository.findByPassport(form.getPassport());
        if(!userCandidate.isPresent()) throw new IllegalArgumentException("Пользователь с такими данными не зарегистрирован.");
        if(!(new BCryptPasswordEncoder().matches(form.getPassword(),userCandidate.get().getHashPassword())))
            throw new IllegalArgumentException("Пароль введен неверно.");
        return userCandidate.get();
    }
}
