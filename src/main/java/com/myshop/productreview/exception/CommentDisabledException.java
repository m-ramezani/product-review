package com.myshop.productreview.exception;

public class CommentDisabledException extends RuntimeException {
    public CommentDisabledException() {
    }

    public CommentDisabledException(String message) {
        super(message);
    }
}
