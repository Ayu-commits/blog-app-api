package com.blog.blogappapi.controllers;

import com.blog.blogappapi.config.AppConstants;
import com.blog.blogappapi.entities.Post;
import com.blog.blogappapi.payloads.ApiResponse;
import com.blog.blogappapi.payloads.PostDto;
import com.blog.blogappapi.payloads.PostResponse;
import com.blog.blogappapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId)
    {
            PostDto createPost = postService.createPost(postDto,userId,categoryId);
            return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
    }
    //getByUser
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> posts =  postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    //getByCategory
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtos =  postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    //getAllPosts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAll(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false)Integer pageNumber,
                                               @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_NUMBER ,required = false) Integer pageSize,
                                               @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY ,required = false)String sortBy,
                                               @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR , required = false) String sortDir)
    {
        PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    //getByID
    @GetMapping("/posts/{postId}")
    public ResponseEntity <PostDto> getById(@PathVariable Integer postId){
        PostDto allPost =  postService.getPostByID(postId);
        return new ResponseEntity<PostDto>(allPost,HttpStatus.OK);

    }

    //delete Post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId)
    {
        postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
    {
        PostDto updatePost = postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }
    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
        @PathVariable("keywords") String keywords){
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);


    }
}

