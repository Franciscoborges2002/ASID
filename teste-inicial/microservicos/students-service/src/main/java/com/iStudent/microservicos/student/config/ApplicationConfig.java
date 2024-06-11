package com.iStudent.microservicos.student.config;

import com.iStudent.microservicos.student.model.Mark;
import com.iStudent.microservicos.student.model.Student;
import com.iStudent.microservicos.student.model.enums.GenderEnum;
import com.iStudent.microservicos.student.model.enums.MarkEnum;
import com.iStudent.microservicos.student.repository.MarkRepository;
import com.iStudent.microservicos.student.repository.StudentRepository;
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

    //Load data into the bds
    @Bean
    CommandLineRunner initiateMark(MarkRepository repository) {
        return args -> {
            Mark mark = new Mark();

            //Set grade
            mark.setMark(MarkEnum.AVERAGE);

            //save into the repository
            repository.save(mark);
        };
    }
    @Bean
    CommandLineRunner initiateStudent(StudentRepository repository) {
        return args -> {
            Student student = new Student();

            //Set base person info
            student.setFirstName("Francisco");
            student.setMiddleName("Manuel");
            student.setLastName("Borges");
            student.setEGN("0123456789");
            student.setAge(21);
            student.setGender(GenderEnum.M);
            student.setEmail("a@a.com");

            //student
            student.setEnrollDate(LocalDate.of(2020, 1, 8));

            //save into the repository
            repository.save(student);
        };
    }
}
