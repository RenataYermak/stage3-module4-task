package com.mjc.school.controller.impl;


import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/newss")
public class NewsRestController implements NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<NewsResponseDto>> readAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                         @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                                         @RequestParam(value = "sort_by", required = false, defaultValue = "name") String sortBy) {
        var newsDTOResponses = newsService.readAll(page, size, sortBy);
        return new ResponseEntity<>(newsDTOResponses, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<NewsResponseDto> readById(@PathVariable("id") Long id) {
        var newsResponseDto = newsService.readById(id);
        return new ResponseEntity<>(newsResponseDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    public ResponseEntity<NewsResponseDto> create(@Validated @RequestBody NewsRequestDto createRequest) {
        var newsResponseDto = newsService.create(createRequest);
        return new ResponseEntity<>(newsResponseDto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<NewsResponseDto> update(@PathVariable("id") Long id,
                                                  @Validated @RequestBody NewsRequestDto updateRequest) {
        var newsResponseDto = newsService.update(updateRequest);
        return new ResponseEntity<>(newsResponseDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable("id") Long id) {
        newsService.deleteById(id);
    }
}