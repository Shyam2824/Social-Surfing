package com.shyam.demo.repository;

import com.shyam.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{

   User findByEmailId(String emailId);

    @Query("select u from User u where u.firstName LIKE%:query% OR u.lastName LIKE%:query% OR u.emailId LIKE%:query%")
   List<User> searchUser(@Param("query") String query);
}
