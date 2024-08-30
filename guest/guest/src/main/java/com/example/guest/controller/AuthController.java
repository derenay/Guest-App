package com.example.guest.controller;

import com.example.guest.model.UserModel;
import com.example.guest.service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authUser(@RequestBody UserModel user, HttpSession session) {
        boolean isAuthenticated = userService.authUser(user.getUsername(), user.getPassword_account());

        if (isAuthenticated) {
            session.setAttribute("user", user);

            // Başarılı girişte JSON yanıtı döndür
            AuthResponse response = new AuthResponse(true, "/visitors");
            return ResponseEntity.ok(response);
        } else {
            // Başarısız girişte JSON yanıtı döndür
            AuthResponse response = new AuthResponse(false, "Kullanıcı adı veya şifre hatalı");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Invalidate the session to log the user out
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        UserModel createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Yanıt yapısını tanımlayan sınıf
    public static class AuthResponse {
        private boolean success;
        private String message;

        public AuthResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }


}