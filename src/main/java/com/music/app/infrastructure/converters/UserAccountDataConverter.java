package com.music.app.infrastructure.converters;

import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.model.UserAccountData;
import com.music.app.infrastructure.repositories.entities.UserAccountEntity;
import com.music.app.infrastructure.wrappers.UserAccountDataWrapper;

import java.util.Optional;

public class UserAccountDataConverter {
    public static UserAccountEntity convertToEntity(UserAccountData userAccountData) {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setEmail(userAccountData.email());
        userAccountEntity.setPassword(userAccountData.password());
        return userAccountEntity;
    }

    public static Optional<UserAccountDto> convertToDto(Optional<UserAccountEntity> userAccountEntity) {
        return userAccountEntity.map(
                account -> new UserAccountDto(account.getId(), account.getEmail(), account.getPassword())
        );
    }

    public static UserAccountData convertToDomain(UserAccountDataWrapper userAccountDataWrapper) {
        return new UserAccountData(
                userAccountDataWrapper.getEmail(),
                userAccountDataWrapper.getPassword()
        );
    }
}
