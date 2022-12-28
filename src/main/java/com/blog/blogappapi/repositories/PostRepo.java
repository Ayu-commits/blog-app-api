package com.blog.blogappapi.repositories;

import com.blog.blogappapi.entities.Category;
import com.blog.blogappapi.entities.Post;
import com.blog.blogappapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    //@Query("select p from Post p  where p.title like :key") // hibernate problem mai old version
    List<Post> findByTitleContaining(String title); // searching
}
