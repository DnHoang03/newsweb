package com.web.springmvc.newsweb.repository;

import com.web.springmvc.newsweb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
