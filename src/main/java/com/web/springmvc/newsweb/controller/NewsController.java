package com.web.springmvc.newsweb.controller;

import com.web.springmvc.newsweb.dto.NewsDTO;
import com.web.springmvc.newsweb.dto.NewsRespone;
import com.web.springmvc.newsweb.service.CommentService;
import com.web.springmvc.newsweb.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<NewsRespone> getAllNews(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false)int pageSize) {
        // Tra ve 1 NewsRespone chua list va cac thong tin lien quan
        return ResponseEntity.ok(newsService.getAllNews(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> newsDetail(@PathVariable Integer id) {

        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<NewsDTO>> newsDetail(@PathVariable int categoryId) {
        return ResponseEntity.ok(newsService.getAllNewsByCategoryId(categoryId));
    }

    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) {
        return new ResponseEntity<>(newsService.createNews(newsDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNews(@RequestBody NewsDTO newsDTO, @PathVariable Integer id) {
        return ResponseEntity.ok(newsService.updateNews(newsDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Integer id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}
