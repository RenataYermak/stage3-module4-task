package com.mjc.school.service.exception;

public enum ErrorCode {

    BAD_REQUEST("400", "Bad Request"),
    NOT_FOUND("404", "Not Found"),
    NEWS_ID_DOES_NOT_EXIST("1", "News with id %d does not exist."),
    AUTHOR_ID_DOES_NOT_EXIST("2", "Author with id %d does not exist."),
    TAG_ID_DOES_NOT_EXIST("3", "Tag with id %d does not exist."),
    COMMENT_ID_DOES_NOT_EXIST("4", "Comment with id %d does not exist."),
    AUTHOR_DOES_NOT_EXIST_FOR_NEWS_ID("5", "Author not found for news with id \"%d\"."),
    COMMENT_DOES_NOT_EXIST_FOR_NEWS_ID("6", "Comment not found for news with id \"%d\"."),
    STRING_VALIDATION("7",
            "Text \"%s\" does not meet requirements: null, less or more than necessary \"%s\"."),
    NUMBER_VALIDATION("8",
            "Number \"%d\" does not meet requirements: negative, null, less or more than necessary \"%s\"."),
    VALIDATION("9", "Validation failed: \"%s\"");

    private final String errorMessage;

    ErrorCode(String errorCode, String message) {
        this.errorMessage = String.format("Error code: %s, Error message: %s", errorCode,message);
    }

    public String getMessage() {
        return errorMessage;
    }
}
