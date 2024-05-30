package com.music.app.domain.services;

import com.music.app.domain.exceptions.ExistentAccountException;
import com.music.app.domain.ports.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckUserAccountExistenceService {

    public static final String THERE_IS_ALREADY_AN_ACCOUNT_WITH_EMAIL_S = "There is already an account with email %s";

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void checkIfExists(String email) {
        if(this.userAccountRepository.findUserAccountByEmail(email).isPresent())
            throw new ExistentAccountException(String.format(THERE_IS_ALREADY_AN_ACCOUNT_WITH_EMAIL_S, email)
        );
    }
}
