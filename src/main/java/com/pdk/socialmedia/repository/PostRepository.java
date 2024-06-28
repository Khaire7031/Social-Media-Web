package com.pdk.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pdk.socialmedia.models.Post;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // @Query("Select p from Post where p.userId =: userId")
    // List<Post> findPostById(Integer userId);

    @Query("SELECT p FROM Post p WHERE p.id = :userId")
    List<Post> findPostById(@Param("userId") Integer userId);
}
