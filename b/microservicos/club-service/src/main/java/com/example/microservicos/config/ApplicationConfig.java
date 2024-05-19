package com.example.microservicos.config;

import com.example.microservicos.model.Club;
import com.example.microservicos.repository.ClubRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

    //Load data into the bds
    @Bean
    CommandLineRunner initiateCountry(ClubRepository repository) {
        return args -> {
            Club club = new Club();

            club.setName("Chess");
            club.setDescription("Club for chess lovers!");

            repository.save(club);
        };
    }
}
