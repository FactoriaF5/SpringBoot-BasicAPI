package com.factoria.coders.services;

import com.factoria.coders.models.User;

public interface IUserService {
    User findById(Long id);
}
