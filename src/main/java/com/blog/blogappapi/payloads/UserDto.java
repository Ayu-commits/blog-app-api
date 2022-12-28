package com.blog.blogappapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "User name must be min of 4 char")
    private String name ;
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10 , message = "Password must be min of 3 char and max of 10 chars !!")
    private String password;
    @NotNull
    private String about;
}
// directly data ko expose kar sakte API mai