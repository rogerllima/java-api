package com.example.login_auth_api.errorHandler;

public class UnauthorizedTokenException extends RuntimeException{
    public UnauthorizedTokenException (String message) {
        super(message);
    }
}
