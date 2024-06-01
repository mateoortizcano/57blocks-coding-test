package com.music.app.infrastructure.services;

import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.exceptions.InvalidCredentialsException;
import com.music.app.domain.ports.IFindUserService;
import com.music.app.domain.ports.IUserAccountRepository;
import com.music.app.domain.validators.ArgumentsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindUserService implements UserDetailsService, IFindUserService {
    public static final String THERE_IS_NO_ACCOUNT_REGISTERED_WITH_EMAIL_S = "There is no account registered with email %s";
    public static final String EMAIL_IS_REQUIRED = "Email is required.";
    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Override
    public UserAccountDto findUser(String email) {
        ArgumentsValidator.verifyNotNullOrEmpty(email, EMAIL_IS_REQUIRED);
        return this.userAccountRepository.findUserAccountByEmail(email)
                .orElseThrow(
                        () -> new InvalidCredentialsException(String.format(THERE_IS_NO_ACCOUNT_REGISTERED_WITH_EMAIL_S, email))
                );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findUser(username);
    }
}
