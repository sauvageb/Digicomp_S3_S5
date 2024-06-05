package com.azqore.demo.service;

import com.azqore.demo.entity.User;
import com.azqore.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchUsers(){
        return (List<User>) userRepository.findAll();
    }
}
