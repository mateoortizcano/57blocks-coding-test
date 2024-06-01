package com.music.app.application.managers.user;

import com.music.app.domain.model.UserAccountData;
import com.music.app.domain.services.user.CreateUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CreateUserAccountDataManager {

    @Autowired
    private CreateUserAccountService createUserAccountService;

    public void execute(UserAccountData userAccountData) {
        createUserAccountService.execute(userAccountData);
    }
}
