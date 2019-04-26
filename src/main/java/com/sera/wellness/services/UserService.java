package com.sera.wellness.services;

import com.sera.wellness.forms.UserLoginForm;
import com.sera.wellness.forms.UserProfileForm;
import com.sera.wellness.forms.UserRegistrationForm;
import com.sera.wellness.models.Friend;
import com.sera.wellness.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService  {
    void signUp(UserRegistrationForm form) ;
    void updateUser(UserProfileForm form);
    Optional<User> getThis(Long id);
    List<Friend> getFriends(Long userId);
    List<User> findAllByName(String name);
    void addToFriend(Long userId, Long friendId);
    Optional<User> getFriend(Long userId, Long friendId);


}
