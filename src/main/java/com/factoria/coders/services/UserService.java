package com.factoria.coders.services;

import com.factoria.coders.models.User;
import com.factoria.coders.repositories.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }
}
