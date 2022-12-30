package com.rohit.fullstackbackend.controller;

import com.rohit.fullstackbackend.domain.User;
import com.rohit.fullstackbackend.exception.UserNotFoundException;
import com.rohit.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/adduser")
    User newUser(@RequestBody User user) {

        return userRepository.save(user);

    }

    @GetMapping("/getusers")
    List<User> getAllUser() {

        return userRepository.findAll();
    }

    @GetMapping("user/{id}")
    User getUserById(@PathVariable Long id) throws UserNotFoundException {

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName((newUser.getUserName()));
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) throws UserNotFoundException {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }



}
