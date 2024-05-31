package com.music.app.infrastructure.converters;

import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.entities.UserAccountData;
import com.music.app.infrastructure.repositories.entities.UserAccountEntity;

import java.util.Optional;

public class UserAccountEntityConverter {
    public static UserAccountEntity convertToEntity(UserAccountData userAccountData) {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setEmail(userAccountData.getEmail());
        userAccountEntity.setPassword(userAccountData.getPassword());
        return userAccountEntity;
    }

    public static Optional<UserAccountDto> convertToDto(Optional<UserAccountEntity> userAccountEntity) {
        return userAccountEntity.map(
                account -> new UserAccountDto(account.getEmail(), account.getPassword())
        );
    }
}
