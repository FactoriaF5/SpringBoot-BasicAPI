package com.factoria.coders.auth.facade;

import com.factoria.coders.models.User;

import java.util.Optional;

public interface IAuthenticationFacade {
    public Optional<User> getAuthUser();
    public boolean isAuthenticated();
}
