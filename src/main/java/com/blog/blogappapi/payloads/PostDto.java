package com.blog.blogappapi.payloads;

import com.blog.blogappapi.entities.Category;
import com.blog.blogappapi.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName = "default.png";
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
