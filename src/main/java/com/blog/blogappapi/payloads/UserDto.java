package com.blog.blogappapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    private String name ;
    private String email;
    private String password;
    private String about;
}
// directly data ko expose kar sakte API mai