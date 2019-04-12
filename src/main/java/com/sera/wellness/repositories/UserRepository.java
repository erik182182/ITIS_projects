package com.sera.wellness.repositories;

import com.sera.wellness.models.User;

import java.util.Optional;

public interface UserRepository extends CRUDRepository<User>{
    Optional<User> findByEmail(String email);
}
