package com.database.databasedemo;

import com.database.databasedemo.entity.Course;
import com.database.databasedemo.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@SpringBootTest
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    public void findAll() {
        List result = entityManager.createQuery("select c from Course c").getResultList(); // JPQL in action. is a sensible language. take care entities' Uppercase
        logger.info("select c from course c -> {}", result);
    }

    @Test
    public void findAll_typed() {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c", Course.class); // This is using a caster to include all result in typed query
        logger.info("select c from course c -> {}", query.getResultList());
    }

    @Test
    public void find_typed() {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where name like '%av%'", Course.class);
        logger.info("select c from Course c where name like '%av%' -> {}", query.getResultList());
    }

}
