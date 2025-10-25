package com.mini_project.challenge_list.controller;

import com.mini_project.challenge_list.model.Challenge;
import com.mini_project.challenge_list.service.ChallengeService;
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

    // Get Challenge by Id
    @GetMapping("/id={id}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable long id){
        System.out.println("\nId: " + id + "\n");
        // Fetch challenge
        Challenge challenge = challengeService.getChallenge(id);


        System.out.println(challenge);
        // Return challenge
        return new ResponseEntity<>(challenge, HttpStatus.OK);
    }

    // Save Challenge by Id
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
