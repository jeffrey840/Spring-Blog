package com.codeup.springinitializer.repositories;

import com.codeup.springinitializer.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}