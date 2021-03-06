package com.database.databasedemo;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.jdbc.PersonDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

//@SpringBootApplication this is to make sure that the JDBC code does not get fired
public class DatabaseJdbcDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonDAO dao;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseJdbcDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All persons -> {}", dao.findAll().toString());
        logger.info("User id 10001 -> {}", dao.findByID(10001));
        logger.info("Deleting User id 10002 -> No rows deleted {}", dao.deleteByID(10002));
        logger.info("Inserting User id 10007 -> No rows inserted {}", dao.insert(new Person(10007, "Tara", "Berlin", new Date())));
        logger.info("Updating User id 10004 -> No rows updated {}", dao.update(new Person(10004, "Pierce", "Amsterdan", new Date())));
    }
}
