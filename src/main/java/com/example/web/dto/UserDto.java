package com.example.web.dto;

import com.example.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 6, max = 255)
    private String password;

    @NotNull
    @Size(min = 2, max = 255)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastName;

    @NotNull
    @Pattern(regexp="[\\d\\+\\s-\\(\\)]{5,64}")
    private String phone;

    @NotNull
    @Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
    private String birthday;

    private String province;

    private String idImg;

    private String idText;

    private String insuranceImg;

    private User.Authority authority;

    private int isQuestionnaireFilled;

    private String timezone;
}
