package com.code.backend.fullstack_backend.controller;

import com.code.backend.fullstack_backend.dto.SearchResponse;
import com.code.backend.fullstack_backend.exception.NewsNotFoundException;
import com.code.backend.fullstack_backend.model.News;
import com.code.backend.fullstack_backend.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @PostMapping("/news")
    public News newUser(@RequestBody News news) {
        return newsRepository.save(news);
    }

    @GetMapping("/news")
    public Page<News> getAllNews(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size) {
        return newsRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/news/{id}")
    public News getNewsById(@PathVariable Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException(id));
    }

    @PutMapping("/news/{id}")
    public News updateNews(@RequestBody News news, @PathVariable Long id) {
        return newsRepository.findById(id)
                .map(n -> {
                    n.setTitle(news.getTitle());
                    n.setSummary(news.getSummary());
                    n.setContent(news.getContent());
                    return newsRepository.save(n);
                }).orElseThrow(() -> new NewsNotFoundException(id));
    }

    @DeleteMapping("/news/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (!newsRepository.existsById(id)) {
            throw new NewsNotFoundException(id);
        }

        newsRepository.deleteById(id);
        return "News with id " + id + " has been deleted success.";
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> searchNews(@RequestParam String title) {
        long startTime = System.currentTimeMillis();

        List<News> newsList = newsRepository.findByTitleContainingIgnoreCase(title);

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        SearchResponse response = new SearchResponse();
        response.setNewsList(newsList);
        response.setTimeTaken(timeTaken);

        return ResponseEntity.ok(response);
    }

}
