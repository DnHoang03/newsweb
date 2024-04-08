package com.web.springmvc.newsweb.controller;

import com.web.springmvc.newsweb.dto.CommentDTO;
import com.web.springmvc.newsweb.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> getAllCommentByNewsId(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getAllCommentByNewsId(id));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(commentDTO), HttpStatus.CREATED);
    }

}
