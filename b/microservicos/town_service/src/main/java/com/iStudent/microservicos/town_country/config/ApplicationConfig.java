package com.iStudent.microservicos.town_country.config;

import com.iStudent.microservicos.town_country.model.Country;
import com.iStudent.microservicos.town_country.model.Town;
import com.iStudent.microservicos.town_country.repository.CountryRepository;
import com.iStudent.microservicos.town_country.repository.TownRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }

    //Load data into the bds
    @Bean
    CommandLineRunner initiateCountry(CountryRepository repository) {
        return args -> {

        };
    }

    @Bean
    CommandLineRunner initiateTown(TownRepository repository, CountryRepository countryRepository) {
        return args -> {
            Country country = new Country();

            country.setName("Portugal");
            country.setNumberCitizens(1000);

            countryRepository.save(country);

            Town town1 = new Town();
            town1.setName("Vila de Prado");
            town1.setCountry(countryRepository.findById(1L).get());
            town1.setNumberCitizens(200);

            Town town2 = new Town();
            town2.setName("Vila Verde");
            town2.setCountry(countryRepository.findById(1L).get());
            town2.setNumberCitizens(500);

            repository.save(town1);
            repository.save(town2);
        };
    }
}
