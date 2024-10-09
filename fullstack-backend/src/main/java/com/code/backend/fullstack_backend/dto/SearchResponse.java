package com.code.backend.fullstack_backend.dto;

import com.code.backend.fullstack_backend.model.News;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponse {
    private List<News> newsList;
    private long timeTaken;
}
