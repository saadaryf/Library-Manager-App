package com.library.libraryManager.exceptions.errors;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String details;

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }

}
