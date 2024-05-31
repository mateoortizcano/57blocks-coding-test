package com.music.app.domain.services.user;

import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.model.UserAccountData;
import com.music.app.domain.exceptions.InvalidCredentialsException;
import com.music.app.domain.ports.IUserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public static final String THERE_IS_NO_ACCOUNT_REGISTERED_WITH_EMAIL_S = "There is no account registered with email %s";
    public static final String THE_PASSWORD_DOES_NOT_MATCH = "The password does not match.";
    @Autowired
    private IUserAccountRepository userAccountRepository;

    public UserAccountDto execute(UserAccountData userAccountData) {
        UserAccountDto userAccountDto = this.userAccountRepository.findUserAccountByEmail(userAccountData.email())
                .orElseThrow(
                        () -> new InvalidCredentialsException(String.format(THERE_IS_NO_ACCOUNT_REGISTERED_WITH_EMAIL_S, userAccountData.email()))
                );
        if (!userAccountData.password().equals(userAccountDto.password())) {
            throw new InvalidCredentialsException(THE_PASSWORD_DOES_NOT_MATCH);
        }
        return userAccountDto;
    }
}
