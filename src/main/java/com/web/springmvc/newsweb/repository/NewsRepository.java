package com.web.springmvc.newsweb.repository;

import com.web.springmvc.newsweb.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByCategoryId(int categoryId);
}
