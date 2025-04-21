package com.example.itinerarymaker.service;

import com.example.itinerarymaker.domain.User;
import javassist.NotFoundException;

public interface UserService {

    public User findByUsernameAndPassword(String username, String password) throws NotFoundException;
}
