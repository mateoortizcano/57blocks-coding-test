package com.music.app.domain.ports;

import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.model.UserAccountData;

import java.util.Optional;

public interface IUserAccountRepository {
    /**
     * Find a user account by email
     *
     * @param email The email of the account to find.
     * @return an optional value. If value is present it represents the user account data
     */
    Optional<UserAccountDto> findUserAccountByEmail(String email);

    /**
     * Register a user account in the repository
     *
     * @param userAccountData The account information to register.
     */
    void createUserAccount(UserAccountData userAccountData);
}
