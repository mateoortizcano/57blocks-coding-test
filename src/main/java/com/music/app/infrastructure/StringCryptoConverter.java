package com.music.app.infrastructure;

import jakarta.persistence.AttributeConverter;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.util.Objects;

public class StringCryptoConverter implements AttributeConverter<String, String> {
    private final StandardPBEStringEncryptor encryptor;

    public StringCryptoConverter(Environment environment, @Value("jasypt.encryptor.password") String jasyptPsswd) {
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword(Objects.requireNonNull(environment.getProperty(jasyptPsswd)));
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor.decrypt(dbData);
    }
}
