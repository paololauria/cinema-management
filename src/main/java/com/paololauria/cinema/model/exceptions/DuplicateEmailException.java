package com.paololauria.cinema.model.exceptions;
public class DuplicateEmailException extends Exception{
    public DuplicateEmailException(String message) {
        super(message);
    }
}
