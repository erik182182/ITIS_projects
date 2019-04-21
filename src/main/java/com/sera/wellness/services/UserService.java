package com.sera.wellness.services;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.forms.UserRegistrationForm;
import com.sera.wellness.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService  {
    void signUp(UserRegistrationForm form) ;



}
