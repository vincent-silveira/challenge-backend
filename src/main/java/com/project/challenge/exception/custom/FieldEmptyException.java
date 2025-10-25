package com.mini_project.challenge_list.exception.custom;

public class FieldEmptyException extends RuntimeException{
    public FieldEmptyException(String fieldName){
        super(fieldName + " cannot be empty");
    }
}
