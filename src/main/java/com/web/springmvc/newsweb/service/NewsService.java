package com.web.springmvc.newsweb.service;

import com.web.springmvc.newsweb.dto.NewsDTO;
import com.web.springmvc.newsweb.dto.NewsRespone;
import com.web.springmvc.newsweb.exception.CategoryNotFoundException;
import com.web.springmvc.newsweb.exception.NewsNotFoundException;
import com.web.springmvc.newsweb.exception.UserNotFoundException;
import com.web.springmvc.newsweb.model.News;
import com.web.springmvc.newsweb.repository.CategoryRepository;
import com.web.springmvc.newsweb.repository.NewsRepository;
import com.web.springmvc.newsweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;



    public NewsDTO createNews(NewsDTO newsDTO) {
        News addedNews = mapToEntity(newsDTO);
        addedNews.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return mapToDTO(newsRepository.save(addedNews));
    }

    public NewsRespone getAllNews(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<News> newsPage = newsRepository.findAll(pageable);
        List<News> news = newsPage.getContent();
        //Map a list<news> to list<newsDTO>
        List<NewsDTO> newsDTOS = news.stream().map(this::mapToDTO).toList();
        NewsRespone newsRespone = new NewsRespone();
        newsRespone.setContent(newsDTOS);
        newsRespone.setPageNumber(newsPage.getNumber());
        newsRespone.setPageSize(newsPage.getSize());
        newsRespone.setTotalElement(newsPage.getTotalElements());
        newsRespone.setTotalPage(newsPage.getTotalPages());
        newsRespone.setLast(newsPage.isLast());
        return newsRespone;
    }

    public NewsDTO getNewsById(Integer id) {
        News news = newsRepository.findById(id).orElseThrow(()->new NewsNotFoundException("Not found news"));
        return mapToDTO(news);
    }

    public NewsDTO updateNews(NewsDTO newsDTO, Integer id) {
        News news = newsRepository.findById(id).orElseThrow(()-> new NewsNotFoundException("Not found news"));
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setThumbnail(newsDTO.getThumbnail());
        news.setShortDescription(newsDTO.getShortDescription());
        news.setCategory(categoryRepository.findById(newsDTO.getCategory()).orElseThrow(()->new CategoryNotFoundException("Not found category for news")));
        News news1 = newsRepository.save(news);
        return mapToDTO(news1);
    }

    public void deleteNews(Integer id) {
        News news = newsRepository.findById(id).orElseThrow(()-> new NewsNotFoundException("Not found news"));
        newsRepository.deleteById(id);
    }

    public List<NewsDTO> getAllNewsByCategoryId(int categoryId) {
        List<News>news = newsRepository.findByCategoryId(categoryId);
        return news.stream().map(this::mapToDTO).toList();
    }

    private NewsDTO mapToDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setTitle(news.getTitle());
        newsDTO.setThumbnail(news.getThumbnail());
        newsDTO.setContent(news.getContent());
        newsDTO.setShortDescription(news.getShortDescription());
        newsDTO.setCategory(news.getCategory().getId());
        newsDTO.setUser(news.getUser().getId());
        return newsDTO;
    }

    private News mapToEntity(NewsDTO newsDTO) {
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setThumbnail(newsDTO.getThumbnail());
        news.setContent(newsDTO.getContent());
        news.setShortDescription(newsDTO.getShortDescription());
        news.setUser(userRepository.findById(newsDTO.getUser()).orElseThrow(()-> new UserNotFoundException("Not found user")));
        news.setCategory(categoryRepository.findById(newsDTO.getCategory()).orElseThrow(()->new CategoryNotFoundException("Not found category")));
        return news;
    }

}
