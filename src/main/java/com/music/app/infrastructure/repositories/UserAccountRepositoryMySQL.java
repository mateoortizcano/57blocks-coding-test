package com.music.app.infrastructure.repositories;

import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.entities.UserAccountData;
import com.music.app.domain.ports.UserAccountRepository;
import com.music.app.infrastructure.converters.UserAccountEntityConverter;
import com.music.app.infrastructure.repositories.entities.UserAccountEntity;
import com.music.app.infrastructure.repositories.jpa.UserAccountRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserAccountRepositoryMySQL implements UserAccountRepository {

    @Autowired
    private UserAccountRepositoryJPA userAccountRepositoryJPA;

    @Override
    public Optional<UserAccountDto> findUserAccountByEmail(String email) {
        return UserAccountEntityConverter.convertToDto(
                this.userAccountRepositoryJPA.findByEmail(email)
        );
    }

    @Override
    public void createUserAccount(UserAccountData userAccountData) {
        UserAccountEntity entity = UserAccountEntityConverter.convertToEntity(userAccountData);
        this.userAccountRepositoryJPA.save(entity);
    }
}
