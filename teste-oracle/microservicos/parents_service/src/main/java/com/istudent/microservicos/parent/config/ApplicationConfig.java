package com.istudent.microservicos.parent.config;

import com.istudent.microservicos.parent.model.Parent;
import com.istudent.microservicos.parent.model.Town;
import com.istudent.microservicos.parent.model.enums.GenderEnum;
import com.istudent.microservicos.parent.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

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

    @Bean
    CommandLineRunner initiateParent1(ParentRepository repository) {
        return args -> {
            Parent parent = new Parent();
            Town town = new Town();
            town.setName("Vila de Prado");

            //Set base person info
            parent.setFirstName("Francisco");
            parent.setMiddleName("Manuel");
            parent.setLastName("Borges");
            parent.setEGN("0123456789");
            parent.setAge(21);
            parent.setGender(GenderEnum.M);
            parent.setEmail("a@a.com");
            parent.setTown("Vila de Prado");

            //student
            parent.setPhoneNumber("351984562771");

            //save into the repository
            repository.save(parent);
        };
    }
}
