package com.blog.blogappapi.services.impl;

import com.blog.blogappapi.Exceptions.ResourceNotFoundException;
import com.blog.blogappapi.entities.Category;
import com.blog.blogappapi.entities.Post;
import com.blog.blogappapi.entities.User;
import com.blog.blogappapi.payloads.PostDto;
import com.blog.blogappapi.payloads.PostResponse;
import com.blog.blogappapi.repositories.CategoryRepo;
import com.blog.blogappapi.repositories.PostRepo;
import com.blog.blogappapi.repositories.UserRepo;
import com.blog.blogappapi.services.PostService;
import org.aspectj.asm.IModelFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(cat);
        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post =  postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);

        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
       Post post =  postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
       postRepo.delete(post);


    }

    @Override
    public PostResponse    getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else
        {
            sort = Sort.by(sortBy).descending();
        }

        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post)->modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostByID(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
       Category cat = categoryRepo.findById(categoryId).
               orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
       List<Post> posts = postRepo.findByCategory(cat);
       List<PostDto> postDtos =  posts.stream().map((post) -> modelMapper.map(post,PostDto.class)).
               collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtos =  posts.stream().map((post) ->this.modelMapper.map(post,PostDto.class) ).
                collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
