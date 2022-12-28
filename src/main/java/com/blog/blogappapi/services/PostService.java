package com.blog.blogappapi.services;

import com.blog.blogappapi.entities.Post;
import com.blog.blogappapi.payloads.PostDto;
import com.blog.blogappapi.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId,Integer categoryID);

    //update
    PostDto updatePost(PostDto postDto ,Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAllPost
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //getSinglePost
    PostDto getPostByID(Integer postId);

    //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by User
    List<PostDto> getPostByUser(Integer userId);

    //search posts
    List<PostDto> searchPosts(String keyword);


}
