package com.sera.wellness.repositories;

import com.sera.wellness.models.Friend;
import com.sera.wellness.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CRUDRepository<User>{
    Optional<User> findByEmail(String email);
    List<Friend> getFriends(Long userId);
    List<User> findAllByName(String name);
    void addToFriend(Long userId, Long friendId);
    Optional<User> getFriend(Long userId, Long friendId);
}
