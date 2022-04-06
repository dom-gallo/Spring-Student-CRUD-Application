package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student dom = new Student(

                    "Dom",
                    "dgallo519@gmail.com",
                    LocalDate.of(1995, 04, 20)
            );
            Student alex = new Student(

                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(1993, 04, 20)
            );
            Student mike = new Student(

                    "Mike",
                    "Mike@gmail.com",
                    LocalDate.of(2000, 04, 20)
            );
            Student john = new Student(

                    "John",
                    "John@gmail.com",
                    LocalDate.of(1995, 04, 20)
            );
            repository.saveAll(
                    List.of(dom, mike, alex, john)
            );
        };

    }
}
