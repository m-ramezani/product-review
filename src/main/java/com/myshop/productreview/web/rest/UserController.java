package com.myshop.productreview.web.rest;


import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.dto.UserDto;
import com.myshop.productreview.exception.UserAlreadyExistsException;
import com.myshop.productreview.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid CreateUserDto createUserDto) throws UserAlreadyExistsException {
        return ResponseEntity.ok(userService.register(createUserDto));
    }

}
