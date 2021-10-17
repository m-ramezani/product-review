package com.myshop.productreview.mapper;

import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.dto.UserDto;
import com.myshop.productreview.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-16T17:32:03+0330",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(CreateUserDto createUserDto) {
        if ( createUserDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( createUserDto.getUserName() );
        user.setPassword( createUserDto.getPassword() );
        user.setRoleType( createUserDto.getRoleType() );
        user.setFirstName( createUserDto.getFirstName() );
        user.setLastName( createUserDto.getLastName() );
        user.setEmail( createUserDto.getEmail() );
        user.setMobileNumber( createUserDto.getMobileNumber() );
        user.setAddress( createUserDto.getAddress() );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUserName( user.getUserName() );
        userDto.setRoleType( user.getRoleType() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setEmail( user.getEmail() );
        userDto.setMobileNumber( user.getMobileNumber() );
        userDto.setAddress( user.getAddress() );

        return userDto;
    }
}
