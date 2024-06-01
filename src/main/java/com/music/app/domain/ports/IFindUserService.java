package com.music.app.domain.ports;

import com.music.app.domain.dtos.UserAccountDto;

public interface IFindUserService {
    /**
     * Find user by given email in the repository
     *
     * @param email
     * @return The user information
     */
    UserAccountDto findUser(String email);
}
