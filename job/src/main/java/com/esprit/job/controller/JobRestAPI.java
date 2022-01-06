package com.esprit.job.controller;

import com.esprit.job.model.Job;
import com.esprit.job.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/jobs")
public class JobRestAPI {
    private final String title = "Hello, I'm RestTemplate the job Microservice";
    @Autowired
    private JobService jobService;

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }


    @PutMapping(value = "/update/{id}/{etat}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Job> updateJob(@PathVariable(value = "id") int id,
                                              @PathVariable(value = "etat") boolean etat) {
        return new ResponseEntity<>(jobService.updateEtatJob(id, etat), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllJobs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Job> getActiveJobs() {
        return jobService.getAll();
    }
}
