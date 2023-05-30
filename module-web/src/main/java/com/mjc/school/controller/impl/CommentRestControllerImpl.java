package com.mjc.school.controller.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.mjc.school.controller.CommentController;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.dto.CommentRequestDto;
import com.mjc.school.service.dto.CommentResponseDto;
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
@RequestMapping(value = "/api/v1/comments")
public class CommentRestControllerImpl implements CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentRestControllerImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> readAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                           @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                                           @RequestParam(value = "sort_by", required = false, defaultValue = "name") String sortBy) {
        var commentDTOResponses = commentService.readAll(page, size, sortBy);
        return new ResponseEntity<>(commentDTOResponses, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CommentResponseDto> readById(@PathVariable("id") Long id) {
        var commentResponseDto = commentService.readById(id);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    public ResponseEntity<CommentResponseDto> create(@Validated @RequestBody CommentRequestDto createRequest) {
        var commentResponseDto = commentService.create(createRequest);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<CommentResponseDto> update(@PathVariable("id") Long id,
                                                    @Validated @RequestBody CommentRequestDto updateRequest) {
        var commentResponseDto = commentService.update(updateRequest);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }

    @Override
    public ResponseEntity<List<CommentResponseDto>> readByNewsId(Long id) {
        var commentResponseDto = commentService.readByNewsId(id);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @Override
    public CommentResponseDto patch(Long id, JsonPatch patch) {
        return null;
    }
}