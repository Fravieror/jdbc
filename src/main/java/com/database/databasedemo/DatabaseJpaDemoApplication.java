package com.database.databasedemo;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.jdbc.PersonDAO;
import com.database.databasedemo.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DatabaseJpaDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseJpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("User id 10001 -> {}", repository.findByID(10001));
        /*
        logger.info("All persons -> {}", dao.findAll().toString());
        logger.info("Deleting User id 10002 -> No rows deleted {}", dao.deleteByID(10002));
        logger.info("Inserting User id 10007 -> No rows inserted {}", dao.insert(new Person(10007, "Tara", "Berlin", new Date())));
        logger.info("Updating User id 10004 -> No rows updated {}", dao.update(new Person(10004, "Pierce", "Amsterdan", new Date())));
         */
    }
}
