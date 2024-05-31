package com.music.app.infrastructure.controllers;

import com.music.app.infrastructure.converters.UserAccountDataConverter;
import com.music.app.application.managers.user.AuthenticationManager;
import com.music.app.application.managers.user.CreateUserAccountDataManager;
import com.music.app.domain.dtos.TokenDto;
import com.music.app.domain.model.UserAccountData;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User controller")
public class UserController {

    @Autowired
    private CreateUserAccountDataManager createUserAccountDataManager;
    @Autowired
    private AuthenticationManager authenticateUserManager;

    @Operation(summary = "Create a user account")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody UserAccountDataWrapper userAccountDataWrapper) {
        UserAccountData userAccountData = UserAccountDataConverter.convertToDomain(userAccountDataWrapper);
        this.createUserAccountDataManager.execute(userAccountData);
    }

    @Operation(summary = "Authenticates and gets a Bearer token")
    @PostMapping("/login")
    public TokenDto login(@RequestBody UserAccountDataWrapper userAccountDataWrapper) {
        UserAccountData userAccountData = UserAccountDataConverter.convertToDomain(userAccountDataWrapper);
        return this.authenticateUserManager.execute(userAccountData);
    }
}
