package com.example.tringleAtm.Services.Implementation;

import com.example.tringleAtm.Models.User;
import com.example.tringleAtm.Repositories.UserRepository;
import com.example.tringleAtm.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean existsByFirstNameAndLastName(String firstname, String lastname) {
        return userRepository.existsByFirstNameAndLastName(firstname,lastname);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public boolean deposit(String username , String password , Long depositalue) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        user.setDeposit(user.getDeposit() + depositalue);
        return true;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }


}
