package com.knoldus;

public class MovieNotFoundException extends RuntimeException{
    MovieNotFoundException(String s){
        super(s);
    }
}
