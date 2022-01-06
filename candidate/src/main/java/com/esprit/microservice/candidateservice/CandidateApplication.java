package com.esprit.microservice.candidateservice;

import com.esprit.microservice.candidateservice.model.Candidat;
import com.esprit.microservice.candidateservice.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class CandidateApplication {


    public static void main(String[] args) {
        SpringApplication.run(CandidateApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CandidatRepository repository) {
        return args -> {
            Stream.of("Mariem", "Sarra", "Mohamed").forEach(nom -> {
                repository.save(new Candidat(nom));
            });
            repository.findAll().forEach(System.out::println);
        };
    }

}
