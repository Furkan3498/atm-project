package com.example.tringleAtm.Repositories;

import com.example.tringleAtm.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);


    void delete(User user);

    List<User> findAll();

    Optional<User> findById(Long id);



    boolean existsByFirstNameAndLastName(String firstName ,String lastName);

    User findByUsernameAndPassword(String username , String password);

    Optional<User> findByUsername(String userName);

}


