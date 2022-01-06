package com.esprit.job.services;

import com.esprit.job.model.Job;
import com.esprit.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job updateEtatJob(int id, boolean newEtat) {
        if (jobRepository.findById(id).isPresent()) {
            Job existingJob = jobRepository.findById(id).get();
            existingJob.setEtat(newEtat);
            return jobRepository.save(existingJob);
        } else
            return null;
    }

    public List<Job> getAll() {
        return jobRepository.findAll().stream().filter(Job::isEtat).collect(Collectors.toList());
    }
}
