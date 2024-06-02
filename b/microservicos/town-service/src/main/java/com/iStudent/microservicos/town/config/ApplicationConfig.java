package com.iStudent.microservicos.town.config;

import com.iStudent.microservicos.town.model.Country;
import com.iStudent.microservicos.town.model.Town;
import com.iStudent.microservicos.town.repository.CountryRepository;
import com.iStudent.microservicos.town.repository.TownRepository;
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
    CommandLineRunner initiateCountry(CountryRepository repository) {
        return args -> {
            Country country = new Country();

            country.setName("Portugal");

            repository.save(country);
        };
    }

    @Bean
    CommandLineRunner initiateTown(TownRepository repository, CountryRepository countryRepository) {
        return args -> {
            Town town1 = new Town();
            town1.setName("Vila de Prado");
            //town1.setNumberHabitants(500);
            town1.setCountry(countryRepository.findById(1L).get());

            Town town2 = new Town();
            town2.setName("Vila Verde");
            //town2.setNumberHabitants(1005);
            town2.setCountry(countryRepository.findById(1L).get());

            repository.save(town1);
            repository.save(town2);
        };
    }
}
