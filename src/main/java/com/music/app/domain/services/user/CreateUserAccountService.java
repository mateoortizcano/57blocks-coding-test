package com.music.app.domain.services.user;

import com.music.app.domain.model.UserAccountData;
import com.music.app.domain.ports.IUserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserAccountService {

    @Autowired
    private CheckUserAccountExistenceService checkUserAccountExistenceService;
    @Autowired
    private IUserAccountRepository userAccountRepository;

    public void execute(UserAccountData userAccountData) {
        checkUserAccountExistenceService.checkIfExists(userAccountData.email());
        userAccountRepository.createUserAccount(userAccountData);
    }
}
