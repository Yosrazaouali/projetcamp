package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
@RestController
@RequestMapping("/api/user")
    public class userController {
        @Autowired
        private UserService userService;
        @PostMapping("/addUser")
        public User addUser(@RequestBody User u) {
        	return userService.addUser(u);
        }
        @PutMapping("/updateUser/{id}")
        public User updateUser(@RequestBody User u,@PathVariable Integer id) {
        	return userService.updateUser(u, id);
        }

        @GetMapping("/all")
        public List<User> getAllUsers(){
            return userService.getAll();
        }

        @DeleteMapping("/delete/{id}")
        public void deleteUser(@PathVariable Integer id) {
            userService.deleteUser(id);
        }

        @GetMapping("/get/{id}")
        public Optional<User> findById(@PathVariable Integer id) {
            return userService.findById(id);
        }




    }


