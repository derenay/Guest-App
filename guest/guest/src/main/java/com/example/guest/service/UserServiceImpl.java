package com.example.guest.service;

import com.example.guest.model.UserModel;
import com.example.guest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUser(){return userRepository.findAll();}

    @Override
    public UserModel findByUsername(String username) {
        Optional<UserModel> user = Optional.ofNullable(userRepository.findByUsername(username));
        return user.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    @Override
    public UserModel saveUser(UserModel user) {
        user.setPassword_account(passwordEncoder.encode(user.getPassword_account()));
        return userRepository.save(user);
    }

    public boolean authUser(String username, String rawPassword) {
        Optional<UserModel> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (optionalUser.isPresent()) {
            UserModel storedUser = optionalUser.get();
            // Şifreyi karşılaştırmak için passwordEncoder kullanın
            return passwordEncoder.matches(rawPassword, storedUser.getPassword_account());
        } else {
            return false; // Kullanıcı bulunamadı
        }
    }
}
