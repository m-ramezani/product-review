package com.myshop.productreview.unit.controller;

import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.dto.UserDto;
import com.myshop.productreview.service.UserServiceImpl;
import com.myshop.productreview.unit.UserObjectProvider;
import com.myshop.productreview.web.rest.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;


    @Before
    public void setup() {
    }

    @Test
    public void register() {
        CreateUserDto createUserDto = UserObjectProvider.createNewUserDto();
        UserDto userDto = UserObjectProvider.createUserDto();
        when(userService.register(createUserDto)).thenReturn(userDto);
        UserDto result = userController.register(createUserDto).getBody();
        assertEquals(Objects.requireNonNull(result).getUserName(), createUserDto.getUserName());
    }

}
