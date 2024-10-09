package com.code.backend.fullstack_backend.repository;

import com.code.backend.fullstack_backend.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitleContainingIgnoreCase(String title);

    Page<News> findAll(Pageable pageable);
}
