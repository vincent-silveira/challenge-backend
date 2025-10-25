package com.mini_project.challenge_list.service;

import com.mini_project.challenge_list.exception.custom.ChallengeNotFoundException;
import com.mini_project.challenge_list.exception.custom.FieldEmptyException;
import com.mini_project.challenge_list.model.Challenge;
import com.mini_project.challenge_list.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    ChallengeRepository challengeRepository;

    // DI constructor
    ChallengeService(ChallengeRepository challengeRepository){
        this.challengeRepository = challengeRepository;

    }

    // Get all Challenges from DB
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    // Get Challenge by Id from DB
    public Challenge getChallenge(long id) {
        Challenge challenge = challengeRepository.findById(id).orElse(null);
        if(challenge == null){
            throw new ChallengeNotFoundException(id);
        }
        return  challenge;
    }

    // Save Challenge by Id
    public void addChallenge(Challenge challenge) {
        if((challenge.getMonth() == null || challenge.getMonth().isEmpty()) && (challenge.getDescription() ==null || challenge.getDescription().isEmpty())){
            throw new FieldEmptyException("Month and Description");
        }
        else if(challenge.getMonth() == null || challenge.getMonth().isEmpty()){
            throw new FieldEmptyException("Month");
        }
        else if(challenge.getDescription() ==null || challenge.getDescription().isEmpty()){
            throw new FieldEmptyException("Description");
        }

        challengeRepository.save(challenge);
    }

    public void deleteChallenge(long id) {
        getChallenge(id);
        challengeRepository.deleteById(id);
    }

    public void updateChallenge(long id, Challenge challenge) {
        getChallenge(id);
        challengeRepository.save(challenge);
    }
}
