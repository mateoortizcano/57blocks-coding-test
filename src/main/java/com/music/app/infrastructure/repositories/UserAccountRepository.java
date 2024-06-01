package com.music.app.infrastructure.repositories;

import com.music.app.infrastructure.converters.UserAccountDataConverter;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.model.UserAccountData;
import com.music.app.domain.ports.IUserAccountRepository;
import com.music.app.infrastructure.repositories.entities.UserAccountEntity;
import com.music.app.infrastructure.repositories.jpa.UserAccountRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserAccountRepository implements IUserAccountRepository {

    @Autowired
    private UserAccountRepositoryJPA userAccountRepositoryJPA;

    @Override
    public Optional<UserAccountDto> findUserAccountByEmail(String email) {
        return UserAccountDataConverter.convertToDto(
                this.userAccountRepositoryJPA.findByEmail(email)
        );
    }

    @Override
    public String createUserAccount(UserAccountData userAccountData) {
        UserAccountEntity entity = UserAccountDataConverter.convertToEntity(userAccountData);
        entity= this.userAccountRepositoryJPA.save(entity);
        this.userAccountRepositoryJPA.flush();
        return entity.getId();
    }
}
