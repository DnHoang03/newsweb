package com.web.springmvc.newsweb.repository;

import com.web.springmvc.newsweb.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByNewsId(int NewsId);
}
