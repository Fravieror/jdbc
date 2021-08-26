package com.database.databasedemo;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.repository.CourseRepository;
import com.database.databasedemo.repository.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DatabaseJpaDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass()); // slf4j logger a lot used.

    @Autowired
    PersonJpaRepository repository;

    @Autowired // Auto-inject the dependency
    private CourseRepository courseRepository;  // It Instantiates repository

    public static void main(String[] args) {
        SpringApplication.run(DatabaseJpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("User id 10001 -> {}", repository.findByID(10001));
        logger.info("Inserting User id 10007 -> {}", repository.update(new Person(10007, "Tara", "Berlin", new Date())));
        logger.info("Updating User id 10004 -> {}", repository.update(new Person(10004, "Pierce", "Amsterdan", new Date())));
        repository.deleteByID(10002);
        logger.info("All persons -> {}", repository.findAll().toString());

        logger.info("Course id 1 -> {}", courseRepository.findById(1L).toString());
    }
}
