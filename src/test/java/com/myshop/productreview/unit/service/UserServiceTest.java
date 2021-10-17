package com.myshop.productreview.unit.service;

import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.dto.UserDto;
import com.myshop.productreview.exception.UserAlreadyExistsException;
import com.myshop.productreview.mapper.UserMapper;
import com.myshop.productreview.model.User;
import com.myshop.productreview.repository.UserRepository;
import com.myshop.productreview.service.UserServiceImpl;
import com.myshop.productreview.unit.UserObjectProvider;
import com.myshop.productreview.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserMapper userMapper;


    @Before
    public void setup() {
    }

    @Test
    public void register() {
        CreateUserDto createUserDto = UserObjectProvider.createNewUserDto();
        User mockUser = UserObjectProvider.createUser();
        doNothing().when(userValidator).validateBeforeRegister(createUserDto);
        when(userMapper.toEntity(createUserDto)).thenReturn(mockUser);
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(userMapper.toDto(mockUser)).thenReturn(UserObjectProvider.createUserDto());
        UserDto userDto = userService.register(createUserDto);
        assertEquals(userDto.getUserName(), createUserDto.getUserName());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void register_when_user_exists() {
        CreateUserDto createUserDto = UserObjectProvider.createNewUserDto();
        doThrow(UserAlreadyExistsException.class).when(userValidator).validateBeforeRegister(createUserDto);
        userService.register(createUserDto);
    }


}
