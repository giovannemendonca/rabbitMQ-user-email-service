package com.ms.user.dto;

import com.ms.user.models.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email
) {

    public UserModel toModel() {
        UserModel model = new UserModel();
        model.setName(name());
        model.setEmail(email());
        return model;
    }
}
