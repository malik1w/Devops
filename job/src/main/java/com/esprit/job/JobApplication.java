package com.esprit.job;

import com.esprit.job.model.Job;
import com.esprit.job.repository.JobRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

    @Bean
    ApplicationRunner init(JobRepository repository) {
        return (args) -> {
            // save
            repository.save(new Job("RH", true));
            repository.save(new Job("IT", false));
            repository.save(new Job("Logistique", true));
            // fetch
            repository.findAll().forEach(System.out::println);

        };
    }

}
