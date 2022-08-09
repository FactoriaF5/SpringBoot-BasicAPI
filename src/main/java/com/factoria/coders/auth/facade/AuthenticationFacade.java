package com.factoria.coders.auth.facade;


import com.factoria.coders.models.User;
import com.factoria.coders.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Autowired
    AuthRepository userRepository;

    public Optional<User> getAuthUser() {

        var userName = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(userName);
    }

    //TODO:  implements isAuthenticated function
    public boolean isAuthenticated() {

        return true;
    }
}
