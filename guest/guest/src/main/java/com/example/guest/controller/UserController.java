package com.example.guest.controller;


import com.example.guest.model.UserModel;

import com.example.guest.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/getallusers")
    public List<UserModel> getAllUsers(HttpSession session){
        if (session.getAttribute("user") == null){
            return userService.getAllUser();
        }
        else {
            return Collections.emptyList();
        }
    }


}
