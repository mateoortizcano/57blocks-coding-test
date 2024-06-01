package com.music.app.domain.services.user;

import com.music.app.domain.exceptions.ExistentAccountException;
import com.music.app.domain.ports.IUserAccountRepository;
import com.music.app.domain.validators.ArgumentsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckUserAccountExistenceService {

    public static final String THERE_IS_ALREADY_AN_ACCOUNT_WITH_EMAIL_S = "There is already an account with email %s";
    public static final String EMAIL_IS_REQUIRED = "Email is required.";

    @Autowired
    private IUserAccountRepository userAccountRepository;

    public void checkIfExists(String email) {
        ArgumentsValidator.verifyNotNullOrEmpty(email, EMAIL_IS_REQUIRED);
        if (this.userAccountRepository.findUserAccountByEmail(email).isPresent())
            throw new ExistentAccountException(String.format(THERE_IS_ALREADY_AN_ACCOUNT_WITH_EMAIL_S, email)
            );
    }
}
