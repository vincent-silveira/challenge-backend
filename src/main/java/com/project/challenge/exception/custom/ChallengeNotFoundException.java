package com.project.challenge.exception.custom;

public class ChallengeNotFoundException extends RuntimeException {
    public ChallengeNotFoundException(long id) {
        super("Could not find Challenge with id: " + id);
    }
}
