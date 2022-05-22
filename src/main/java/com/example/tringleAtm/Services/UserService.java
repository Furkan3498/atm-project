package com.example.tringleAtm.Services;

import com.example.tringleAtm.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    boolean existsByFirstNameAndLastName(String firstname , String lastname);

    Optional<User> findByUsername(String username);

    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    boolean deposit(String username , String password , Long depositalue);

    User findByUsernameAndPassword(String username , String password);

    void removeUser(User user);
}
