package com.mini_project.challenge_list.exception.custom;

public class ChallengeNotFoundException extends RuntimeException {
    public ChallengeNotFoundException(long id) {
        super("Could not find Challenge with id: " + id);
    }
}
