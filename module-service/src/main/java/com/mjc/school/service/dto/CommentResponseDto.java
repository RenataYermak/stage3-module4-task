package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public final class CommentResponseDto {

    private final Long id;
    private final String content;
    private final NewsResponseDto newsDto;
    private final LocalDateTime createDate;
    private final LocalDateTime lastUpdateDate;

    public CommentResponseDto(Long id, String content, NewsResponseDto newsDto, LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.id = id;
        this.content = content;
        this.newsDto = newsDto;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public NewsResponseDto getNewsDto() {
        return newsDto;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }
}
