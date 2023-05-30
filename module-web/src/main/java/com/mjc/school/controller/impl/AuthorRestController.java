package com.mjc.school.controller.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.mjc.school.controller.AuthorController;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping(value = "/api/v1/authors")
public class AuthorRestController implements AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> readAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                           @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                                           @RequestParam(value = "sort_by", required = false, defaultValue = "name") String sortBy) {
        var authorDTOResponses = authorService.readAll(page, size, sortBy);
        return new ResponseEntity<>(authorDTOResponses, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<AuthorResponseDto> readById(@PathVariable("id") Long id) {
        var authorResponseDto = authorService.readById(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    public ResponseEntity<AuthorResponseDto> create(@Validated @RequestBody AuthorRequestDto createRequest) {
        var authorResponseDto = authorService.create(createRequest);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<AuthorResponseDto> update(@PathVariable("id") Long id,
                                                    @Validated @RequestBody AuthorRequestDto updateRequest) {
        var authorResponseDto = authorService.update(updateRequest);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable("id") Long id) {
        authorService.deleteById(id);
    }

    @GetMapping("/news/{id}")
    @Override
    public ResponseEntity<AuthorResponseDto> readByNewsId(@PathVariable("id") Long id) {
        var authorResponseDto = authorService.readByNewsId(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }
}