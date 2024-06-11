package com.iStudent.microservicos.studentmark.config;

import com.iStudent.microservicos.studentmark.model.Mark;
import com.iStudent.microservicos.studentmark.model.Student;
import com.iStudent.microservicos.studentmark.model.enums.GenderEnum;
import com.iStudent.microservicos.studentmark.model.enums.MarkEnum;
import com.iStudent.microservicos.studentmark.repository.MarkRepository;
import com.iStudent.microservicos.studentmark.repository.StudentRepository;
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
    CommandLineRunner initiateMark(MarkRepository repository) {
        return args -> {
            Mark mark1 = new Mark();
            Mark mark2 = new Mark();
            Mark mark3 = new Mark();
            Mark mark4 = new Mark();
            Mark mark5 = new Mark();

            mark1.setMark(MarkEnum.POOR);
            mark2.setMark(MarkEnum.AVERAGE);
            mark3.setMark(MarkEnum.GOOD);
            mark4.setMark(MarkEnum.VERY_GOOD);
            mark5.setMark(MarkEnum.EXCELLENT);

            //save into the repository
            repository.save(mark1);
            repository.save(mark2);
            repository.save(mark3);
            repository.save(mark4);
            repository.save(mark5);

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
            student.setTown("Vila de Prado");

            //student
            student.setEnrollDate(LocalDate.of(2020, 1, 8));

            //save into the repository
            repository.save(student);
        };
    }
}
