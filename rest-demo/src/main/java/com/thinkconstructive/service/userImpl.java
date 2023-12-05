package com.thinkconstructive.service;

import com.thinkconstructive.dao.UserRepository;
import com.thinkconstructive.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userImpl implements  UserService{



    UserRepository userRepository;

    public userImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String createUser(User user) {
        userRepository.save(user);  //
        return " user create Succsessfull ";
    }


    public String updateUser(User user) {
        userRepository.save(user);
        return "update user sucsessfully ";
    }


    public String deleteUser(int userId) {
        userRepository.deleteById(userId);
        return "delete User successfully";
    }


    public User getUser(int userId) {

        return  userRepository.findById(userId).get();
    }


    public List<User> getAlluser() {
        return userRepository.findAll();
    }

    //  MORE BUSSNESS LOGIC WRITE HERE
}
