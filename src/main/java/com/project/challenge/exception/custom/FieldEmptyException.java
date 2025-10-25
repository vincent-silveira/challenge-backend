package com.project.challenge.exception.custom;

public class FieldEmptyException extends RuntimeException{
    public FieldEmptyException(String fieldName){
        super(fieldName + " cannot be empty");
    }
}
