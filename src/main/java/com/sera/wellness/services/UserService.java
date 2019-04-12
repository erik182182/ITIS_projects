package com.sera.wellness.services;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.forms.UserRegistrationForm;
import com.sera.wellness.models.User;


public interface UserService {
    void signUp(UserRegistrationForm form) ;
    void signIn(UserLoginForm form);
    boolean checkCookie(User user, String cookieValue);
}
