package com.paololauria.cinema.model.exceptions;
public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}
