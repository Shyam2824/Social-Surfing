package com.shyam.demo.service;

import com.shyam.demo.Exception.UserException;
import com.shyam.demo.config.JwtProvider;
import com.shyam.demo.models.User;
import com.shyam.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser= new User();
        newUser.setEmailId(user.getEmailId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassWord(user.getPassWord());
        newUser.setId(user.getId());

        User savedUser= userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        Optional<User> user=userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not exist with userid " + userId);
    }

    @Override
    public User findUserByEmailId(String emailId) {
        User user= userRepository.findByEmailId(emailId);
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser=findUserById(reqUserId);
        User user2=findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserException {
        Optional<User> user1=userRepository.findById(userId);

        if(user1.isEmpty()) {
            throw new UserException("user not exist with Id " + userId);
        }
        User oldUser= user1.get();
        if(user.getFirstName()!=null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmailId()!=null){
            oldUser.setEmailId(user.getEmailId());
        }
        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }

        User updateUser= userRepository.save(oldUser);
        return updateUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);

    }

    @Override
    public User findUserByJwt(String jwt) {

        String email= JwtProvider.getEmailFromJwtToken(jwt);

        User user= userRepository.findByEmailId(email);
        return  user;

    }
}
