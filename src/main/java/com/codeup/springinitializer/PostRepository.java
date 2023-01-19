package com.codeup.springinitializer;

import com.codeup.springinitializer.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}