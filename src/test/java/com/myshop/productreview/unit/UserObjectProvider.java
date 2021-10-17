package com.myshop.productreview.unit;

import com.myshop.productreview.dto.CreateUserDto;
import com.myshop.productreview.dto.UserDto;
import com.myshop.productreview.enums.RoleType;
import com.myshop.productreview.model.User;

public class UserObjectProvider {

    private static final String userName = "example_user";
    private static final String userPassword = "example_pass";

    public static User createUser() {
        User user = User.of()
                .id(1L)
                .userName(userName)
                .email("example_email@gmail.com")
                .password(userPassword)
                .mobileNumber("09125435454")
                .firstName("firstName")
                .lastName("lastName")
                .roleType(RoleType.ROLE_USER)
                .build();
        return user;
    }

    public static CreateUserDto createNewUserDto() {
        CreateUserDto createUserDto = CreateUserDto.of()
                .userName(userName)
                .email("example_email@gmail.com")
                .password(userPassword)
                .mobileNumber("09125435454")
                .firstName("firstName")
                .lastName("lastName")
                .roleType(RoleType.ROLE_USER)
                .build();
        return createUserDto;
    }

    public static UserDto createUserDto() {
        UserDto userDto = UserDto.of()
                .id(1L)
                .userName(userName)
                .email("example_email@gmail.com")
                .mobileNumber("09125435454")
                .firstName("firstName")
                .lastName("lastName")
                .roleType(RoleType.ROLE_USER)
                .build();
        return userDto;
    }

}
