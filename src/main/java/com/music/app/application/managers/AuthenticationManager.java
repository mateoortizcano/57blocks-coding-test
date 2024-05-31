package com.music.app.application.managers;

import com.music.app.domain.dtos.TokenDto;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.entities.UserAccountData;
import com.music.app.domain.ports.TokenGenerator;
import com.music.app.domain.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private TokenGenerator tokenGenerator;

    public TokenDto execute(UserAccountData userAccountData) {
        UserAccountDto authenticatedUserAccount = authenticationService.execute(userAccountData);
        return this.tokenGenerator.generateToken(authenticatedUserAccount);
    }
}
