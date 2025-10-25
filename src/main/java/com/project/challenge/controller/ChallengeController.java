package com.project.challenge.controller;

import com.project.challenge.model.Challenge;
import com.project.challenge.service.ChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000/")

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    ChallengeService challengeService;

    // DI constructor
    ChallengeController(ChallengeService challengeService){
        this.challengeService = challengeService;
    }

    // Get all Challenges
    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges(){

        // Fetch challenge list
        List<Challenge> challengeList = challengeService.getAllChallenges();

        // Return the list in response entity
        return new ResponseEntity<>(challengeList, HttpStatus.OK);
    }

    // Get Challenge by ID
    @GetMapping("/id={id}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable long id){
        // Fetch challenge
        Challenge challenge = challengeService.getChallenge(id);

        // Return challenge
        return new ResponseEntity<>(challenge, HttpStatus.OK);
    }

    // Save Challenge by ID
    @PostMapping
    public ResponseEntity<Boolean> addChallenge(@RequestBody Challenge challenge){
        challengeService.addChallenge(challenge);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<Boolean> deleteChallenge(@PathVariable long id){
        challengeService.deleteChallenge(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<Boolean> updateChallenge(@PathVariable long id, @RequestBody Challenge challenge){
        challengeService.updateChallenge(id, challenge);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
