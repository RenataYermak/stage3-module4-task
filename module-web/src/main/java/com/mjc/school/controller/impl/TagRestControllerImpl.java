package com.mjc.school.controller.impl;

import com.github.fge.jsonpatch.JsonPatch;
import com.mjc.school.controller.TagController;
import com.mjc.school.controller.BaseController;
import com.mjc.school.service.TagService;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.CommentResponseDto;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.dto.TagResponseDto;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.dto.TagResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/api/v1/tags")
public class TagRestControllerImpl implements TagController {

    private final TagService tagService;

    @Autowired
    public TagRestControllerImpl(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TagResponseDto>> readAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                           @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                                           @RequestParam(value = "sort_by", required = false, defaultValue = "name") String sortBy) {
        var tagDTOResponses = tagService.readAll(page, size, sortBy);
        return new ResponseEntity<>(tagDTOResponses, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TagResponseDto> readById(@PathVariable("id") Long id) {
        var tagResponseDto = tagService.readById(id);
        return new ResponseEntity<>(tagResponseDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    public ResponseEntity<TagResponseDto> create(@Validated @RequestBody TagRequestDto createRequest) {
        var tagResponseDto = tagService.create(createRequest);
        return new ResponseEntity<>(tagResponseDto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<TagResponseDto> update(@PathVariable("id") Long id,
                                                    @Validated @RequestBody TagRequestDto updateRequest) {
        var tagResponseDto = tagService.update(updateRequest);
        return new ResponseEntity<>(tagResponseDto, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable("id") Long id) {
        tagService.deleteById(id);
    }

    @Override
    public ResponseEntity<List<TagResponseDto>> readByNewsId(Long id) {
        var tagResponseDto = tagService.readByNewsId(id);
        return new ResponseEntity<>(tagResponseDto, HttpStatus.OK);
    }

    @Override
    public TagResponseDto patch(Long id, JsonPatch patch) {
        return null;
    }
}