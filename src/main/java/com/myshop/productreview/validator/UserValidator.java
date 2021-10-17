package com.myshop.productreview.validator;

import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.exception.UserAlreadyExistsException;
import com.myshop.productreview.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void validateBeforeRegister(CreateUserDto createUserDto) {
        if (userRepository.findByUserName(createUserDto.getUserName()).isPresent()) {
            throw new UserAlreadyExistsException("User with user name " + createUserDto.getUserName() + " already exists");
        }
    }
}
