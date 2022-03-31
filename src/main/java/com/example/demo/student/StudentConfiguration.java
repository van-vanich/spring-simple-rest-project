package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student first = new Student(
                    1L,
                    "First",
                    "first@gmail.com",
                    LocalDate.of(2003, Month.FEBRUARY, 13)
            );

            Student second = new Student(
                    "Second",
                    "second@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 8)
            );

            studentRepository.saveAll(List.of(first, second));
        };
    }
}
