package com.music.app.application.managers;

import com.music.app.domain.entities.UserAccountData;
import com.music.app.domain.services.CreateUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserAccountDataManager {

    @Autowired
    private CreateUserAccountService createUserAccountService;

    public void execute(UserAccountData userAccountData) {
        createUserAccountService.execute(userAccountData);
    }
}
