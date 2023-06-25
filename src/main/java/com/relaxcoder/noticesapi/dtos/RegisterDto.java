package com.relaxcoder.noticesapi.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String username;
    @Email
    private String email;
    @Size(min = 8, message = "Password should be at least 8 Characters Long")
    private String password;
    @NotEmpty
    private String role;
}
