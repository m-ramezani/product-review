package com.myshop.productreview.mapper;

import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.dto.UserDto;
import com.myshop.productreview.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(CreateUserDto createUserDto);

    UserDto toDto(User user);
}
