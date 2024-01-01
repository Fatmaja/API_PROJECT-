package com.example.apiproject.exceptions;

public class UserNotFoundException extends CustomExceptions {

    public UserNotFoundException(String userEmail) {
        super("User not found for email: " + userEmail);
    }
}
