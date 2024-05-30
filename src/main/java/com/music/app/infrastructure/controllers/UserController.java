package com.music.app.infrastructure.controllers;

import com.music.app.application.converters.UserAccountDataConverter;
import com.music.app.application.managers.CreateUserAccountDataManager;
import com.music.app.domain.entities.UserAccountData;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "User controller")
public class UserController {

    @Autowired
    private UserAccountDataConverter userAccountDataConverter;
    @Autowired
    private CreateUserAccountDataManager createUserAccountDataManager;

    @Operation(summary = "Create a user account")
    @PostMapping("/create")
    public void create(@RequestBody UserAccountDataWrapper userAccountDataWrapper) {
        UserAccountData userAccountData = userAccountDataConverter.convertToDomain(userAccountDataWrapper);
        createUserAccountDataManager.execute(userAccountData);
    }
}
