package com.music.app.infrastructure.controllers;

import com.music.app.application.converters.UserAccountDataConverter;
import com.music.app.application.managers.AuthenticationManager;
import com.music.app.application.managers.CreateUserAccountDataManager;
import com.music.app.domain.dtos.TokenDto;
import com.music.app.domain.entities.UserAccountData;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User controller")
public class UserController {

    @Autowired
    private UserAccountDataConverter userAccountDataConverter;
    @Autowired
    private CreateUserAccountDataManager createUserAccountDataManager;

    @Autowired
    private AuthenticationManager authenticateUserManager;

    @Operation(summary = "Create a user account")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserAccountDataWrapper userAccountDataWrapper) {
        UserAccountData userAccountData = this.userAccountDataConverter.convertToDomain(userAccountDataWrapper);
        this.createUserAccountDataManager.execute(userAccountData);
    }

    @Operation(summary = "Authenticates and gets a Bearer token")
    @PostMapping("/login")
    public TokenDto login(@RequestBody UserAccountDataWrapper userAccountDataWrapper) {
        UserAccountData userAccountData = userAccountDataConverter.convertToDomain(userAccountDataWrapper);
        return this.authenticateUserManager.execute(userAccountData);
    }
}
