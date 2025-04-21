package com.example.itinerarymaker.service.impl;

import com.example.itinerarymaker.domain.User;
import com.example.itinerarymaker.repository.UserRepository;
import com.example.itinerarymaker.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws NotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new NotFoundException("Wrong username or password!");
        }

        return user;
    }
}
