package com.music.app.domain.ports;

import com.music.app.domain.dtos.TokenDto;
import com.music.app.domain.dtos.UserAccountDto;

public interface TokenGenerator {

    /**
     * Creates and returns a Token with the information and expiration
     *
     * @param authenticatedUserAccount The user data
     * @return The token information
     */
    TokenDto generateToken(UserAccountDto authenticatedUserAccount);

    /**
     * Verifies if the Token provided is valid acording to user account email and expiration
     *
     * @param token            The Token
     * @param accountUserEmail The user account email who is trying to perform an action
     * @return True if the Token is still valid
     */
    boolean isTokenValid(String token, String accountUserEmail);
}
