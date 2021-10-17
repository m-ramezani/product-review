package com.myshop.productreview.dto;

import com.myshop.productreview.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class UserDto implements Serializable {
    private Long id;

    private String userName;

    private RoleType roleType;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private String address;

}
