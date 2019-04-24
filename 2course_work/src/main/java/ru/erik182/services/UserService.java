package ru.erik182.services;

import ru.erik182.models.User;
import ru.erik182.models.forms.SignInForm;

public interface UserService {
    User singIn(SignInForm form);
}
