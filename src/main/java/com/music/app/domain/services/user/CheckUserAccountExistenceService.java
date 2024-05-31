package com.music.app.domain.services.user;

import com.music.app.domain.exceptions.ExistentAccountException;
import com.music.app.domain.ports.IUserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckUserAccountExistenceService {

    public static final String THERE_IS_ALREADY_AN_ACCOUNT_WITH_EMAIL_S = "There is already an account with email %s";

    @Autowired
    private IUserAccountRepository userAccountRepository;

    public void checkIfExists(String email) {
        if (this.userAccountRepository.findUserAccountByEmail(email).isPresent())
            throw new ExistentAccountException(String.format(THERE_IS_ALREADY_AN_ACCOUNT_WITH_EMAIL_S, email)
            );
    }
}
