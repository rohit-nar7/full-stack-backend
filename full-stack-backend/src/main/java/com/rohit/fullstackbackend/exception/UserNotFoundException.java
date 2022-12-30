package com.rohit.fullstackbackend.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(Long id){

        super("user not found with id: "+id);
    }
}
