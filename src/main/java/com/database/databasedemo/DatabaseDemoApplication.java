package com.database.databasedemo;

import com.database.databasedemo.jdbc.PersonDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonDAO dao;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All persons -> {}", dao.findAll().toString());
        logger.info("User id 10001 -> {}", dao.findByID(10001));
        logger.info("Deleting User id 10002 -> No rows Deleted {}", dao.deleteByID(10002));
    }
}
