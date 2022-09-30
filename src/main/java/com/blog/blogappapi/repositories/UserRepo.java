package com.blog.blogappapi.repositories;

import com.blog.blogappapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
