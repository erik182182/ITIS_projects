package com.sera.wellness.repositories.mappers;

import com.sera.wellness.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .hashPassword(resultSet.getString("hash_password"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .sex(resultSet.getByte("sex"))
                .consentToReceiveEmails(resultSet.getBoolean("consent_to_receive_emails"))
                .age(resultSet.getInt("age"))
                .weight(resultSet.getInt("weight"))
                .growth(resultSet.getInt("growth"))
                .purposeWeight(resultSet.getInt("purpose_weight"))
                .build();
    }
}
