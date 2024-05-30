package com.music.app.application.converters;

import com.music.app.domain.entities.UserAccountData;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDataConverter {
    public UserAccountData convertToDomain(UserAccountDataWrapper userAccountDataWrapper) {
        return new UserAccountData(
                userAccountDataWrapper.getEmail(),
                userAccountDataWrapper.getPassword()
        );
    }
}
