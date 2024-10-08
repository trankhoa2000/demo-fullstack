package com.code.backend.fullstack_backend.repository;

import com.code.backend.fullstack_backend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
