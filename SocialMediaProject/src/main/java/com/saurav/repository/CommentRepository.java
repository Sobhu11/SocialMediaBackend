package com.saurav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurav.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
