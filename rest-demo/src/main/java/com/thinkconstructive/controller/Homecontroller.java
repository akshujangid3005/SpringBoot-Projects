package com.thinkconstructive.controller;


import com.thinkconstructive.entity.User;
import com.thinkconstructive.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class Homecontroller {

  UserService userService;

    public Homecontroller(UserService userService) {
        this.userService = userService;
    }

    // get one user details
    @GetMapping("{userid}")
    public User getUserdetails(@PathVariable("userid") int userid)
    {
        return  userService.getUser(userid);
    }

    // All user details
    @GetMapping()
    public List<User>  getAllUserdetails()
    {
        return  userService.getAlluser();
    }

    @PostMapping
    public String createUserdetails(@RequestBody User user)
    {
    userService.createUser(user);
    return "Created user successfully";
    }

    @PutMapping
    public String updateUserdetails(@RequestBody User user)
    {
        userService.updateUser(user);
        return "Updated  user successfully";
    }

    @DeleteMapping("/{userid}")
    public String deleteUserdetails( @PathVariable("userid")  int userid)
    {
        userService.deleteUser(userid);
        return "delete  user successfully";
    }
}
