package com.example.tringleAtm;

import com.example.tringleAtm.Models.User;
import com.example.tringleAtm.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerSetup implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String...args) throws Exception {

        // Upload Example Data

        User user1 = new User("furkan" , "ceylan" , "111@gmail.com" , "furkan", passwordEncoder.encode("password"));


        userRepository.save(user1);




    }
}
