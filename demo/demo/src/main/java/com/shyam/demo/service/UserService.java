package com.shyam.demo.service;

import com.shyam.demo.Exception.UserException;
import com.shyam.demo.models.User;

import java.util.List;

public interface UserService {

         User registerUser(User user);

        User findUserById(Integer userId) throws UserException;

         User findUserByEmailId(String emailId);

         User followUser(Integer userId1, Integer userId2) throws UserException;

         User updateUser(User user, Integer userId) throws UserException;

        List <User>searchUser(String query);

        User findUserByJwt(String jwt);

}
