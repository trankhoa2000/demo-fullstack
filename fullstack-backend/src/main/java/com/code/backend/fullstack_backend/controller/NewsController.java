package com.code.backend.fullstack_backend.controller;

import com.code.backend.fullstack_backend.exception.NewsNotFoundException;
import com.code.backend.fullstack_backend.model.News;
import com.code.backend.fullstack_backend.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @PostMapping("/news")
    News newUser(@RequestBody News news) {
        return newsRepository.save(news);
    }

    @GetMapping("/news")
    List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @GetMapping("/news/{id}")
    News getNewsById(@PathVariable Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException(id));
    }

    @PutMapping("/news/{id}")
    News updateNews(@RequestBody News news, @PathVariable Long id) {
        return newsRepository.findById(id)
                .map(n -> {
                    n.setTitle(news.getTitle());
                    n.setSummary(news.getSummary());
                    n.setContent(news.getContent());
                    return newsRepository.save(n);
                }).orElseThrow(() -> new NewsNotFoundException(id));
    }

    @DeleteMapping("/news/{id}")
    String deleteUser(@PathVariable Long id) {
        if (!newsRepository.existsById(id)) {
            throw new NewsNotFoundException(id);
        }

        newsRepository.deleteById(id);
        return "News with id " + id + " has been deleted success.";
    }
}
