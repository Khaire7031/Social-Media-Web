package com.pdk.socialmedia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdk.socialmedia.models.User;
import com.pdk.socialmedia.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());
        System.out.println(newUser);

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        // User nahi Mila to
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not Found! with ID : " + id);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1 = findUserById(userId1);

        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user, Integer id) throws Exception {
        Optional<User> FindUser = userRepository.findById(id);
        if (FindUser.isEmpty()) {
            throw new Exception("User not Found! with ID : " + id);
        }

        User OldUser = FindUser.get();

        if (user.getFirstName() != null) {
            OldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            OldUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            OldUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            OldUser.setPassword(user.getPassword());
        }
        // System.out.println("Password : " + OldUser.getPassword());

        User updatedUser = userRepository.save(OldUser);
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

}
