package com.sera.wellness.services;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.forms.UserRegistrationForm;
import com.sera.wellness.models.User;
import com.sera.wellness.models.UserAuth;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService  {
    void signUp(UserRegistrationForm form) ;
    UserAuth signIn(UserLoginForm form);
    UserAuth auth(Long id);


}
