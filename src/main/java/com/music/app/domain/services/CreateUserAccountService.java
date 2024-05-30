package com.music.app.domain.services;

import com.music.app.domain.entities.UserAccountData;
import com.music.app.domain.ports.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserAccountService {

    @Autowired
    private CheckUserAccountExistenceService checkUserAccountExistenceService;
    @Autowired
    private UserAccountRepository userAccountRepository;

    public void execute(UserAccountData userAccountData) {
        checkUserAccountExistenceService.checkIfExists(userAccountData.getEmail());
        userAccountRepository.createUserAccount(userAccountData);
    }
}
