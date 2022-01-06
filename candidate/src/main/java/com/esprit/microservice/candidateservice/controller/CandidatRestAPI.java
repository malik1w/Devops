package com.esprit.microservice.candidateservice.controller;

import com.esprit.microservice.candidateservice.model.Candidat;
import com.esprit.microservice.candidateservice.model.Job;
import com.esprit.microservice.candidateservice.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/candidats")
public class CandidatRestAPI {
    private final String title = "Hello, I'm  Candidate Microservice";

    @Autowired
    private CandidatService candidatService;


    @Value("${myrest.url}")
    private String Base_URL;

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        return new ResponseEntity<>(candidatService.addCandidat(candidat), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Candidat> updateCandidat(@PathVariable(value = "id") int id,
                                                   @RequestBody Candidat candidat) {
        return new ResponseEntity<>(candidatService.updateCandidat(id, candidat), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCandidat(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(candidatService.deleteCandidat(id), HttpStatus.OK);
    }

    @GetMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Job> getActiveJobs() {
        return getRestTemplate().getForObject(Base_URL, List.class);
    }

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
