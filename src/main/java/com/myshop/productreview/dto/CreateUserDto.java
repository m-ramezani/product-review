package com.myshop.productreview.dto;

import com.myshop.productreview.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
public class CreateUserDto implements Serializable {
    @NotEmpty
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 30)
    private String password;

    @NotNull
    private RoleType roleType;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "(09)?[0-9]{9}")
    private String mobileNumber;

    @NotEmpty
    private String address;

}
