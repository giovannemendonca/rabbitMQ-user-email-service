package com.ms.user.controller;

import com.ms.user.dto.UserDto;
import com.ms.user.models.UserModel;
import com.ms.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDto userDto) {
        UserModel userModel = userDto.toModel();
        userModel = userService.saveUser(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

}
