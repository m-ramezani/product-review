package com.myshop.productreview.service;

import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.dto.UserDto;
import com.myshop.productreview.mapper.UserMapper;
import com.myshop.productreview.model.User;
import com.myshop.productreview.repository.UserRepository;
import com.myshop.productreview.validator.UserValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserValidator userValidator,
                           UserMapper userMapper, UserRepository userRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userValidator = userValidator;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(CreateUserDto createUserDto) {
        userValidator.validateBeforeRegister(createUserDto);
        User user = userMapper.toEntity(createUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
