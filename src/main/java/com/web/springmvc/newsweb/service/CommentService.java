package com.web.springmvc.newsweb.service;

import com.web.springmvc.newsweb.dto.CommentDTO;
import com.web.springmvc.newsweb.exception.NewsNotFoundException;
import com.web.springmvc.newsweb.exception.UserNotFoundException;
import com.web.springmvc.newsweb.model.Comment;
import com.web.springmvc.newsweb.repository.CommentRepository;
import com.web.springmvc.newsweb.repository.NewsRepository;
import com.web.springmvc.newsweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;


    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.save(mapToEntity(commentDTO));
        return commentDTO;
    }

    public List<CommentDTO> getAllCommentByNewsId(int id) {
        List<Comment> comments = commentRepository.findByNewsId(id);
        return comments.stream().map(this::mapToDTO).toList();
    }


    private CommentDTO mapToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());
        commentDTO.setUserId(comment.getUser().getId());
        commentDTO.setNewsId(comment.getNews().getId());
        return commentDTO;
    }

    private Comment mapToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setNews(newsRepository.findById(commentDTO.getNewsId()).orElseThrow(()-> new NewsNotFoundException("Not found news")));
        comment.setUser(userRepository.findById(commentDTO.getUserId()).orElseThrow(()-> new UserNotFoundException("Not found user")));
        return comment;
    }
}
