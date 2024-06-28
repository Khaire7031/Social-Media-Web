package com.pdk.socialmedia.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pdk.socialmedia.models.User;
import com.pdk.socialmedia.repository.UserRepository;
import com.pdk.socialmedia.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return savedUser;
    }

    @GetMapping("api/user")
    public List<User> getUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("api/user/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        return userService.findUserById(id);
    }

    @PutMapping("api/user/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Integer id) throws Exception {
        User updatedUser = userService.updateUser(user, id);
        return updatedUser;
    }

    @DeleteMapping("api/user/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId) throws Exception {

        Optional<User> FindUser = userRepository.findById(userId);
        if (FindUser.isEmpty()) {
            throw new Exception("User not Found! with ID : " + userId);
        }
        userRepository.delete(FindUser.get());

        return "User Deleted Successfully with is : " + userId;
    }

    @PutMapping("api/user/follow/{userId1}/{userId2}")
    public User follwUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user = userService.followUser(userId1, userId2);
        return user;
    }

    @GetMapping("/user/search")
    public List<User> searchUsers(@RequestParam("query") String query) {
        List<User> users = userService.searchUser(query);
        return users;
    }
}
