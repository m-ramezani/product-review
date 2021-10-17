package com.myshop.productreview.service;

import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.dto.UserDto;

public interface UserService {
    UserDto register(CreateUserDto createUserDto);
}
