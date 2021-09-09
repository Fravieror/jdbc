package com.database.databasedemo;

import com.database.databasedemo.entity.Course;
import com.database.databasedemo.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
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
    public void findWithNamedQueryAll() { // Makes consult with query stored in the entity
        List result = entityManager.createNamedQuery("query_get_all_courses").getResultList(); // JPQL in action. is a sensible language. take care entities' Uppercase
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


    @Test
    public void native_queries_with_named_parameter() {
        Query query = entityManager.createNativeQuery("select * from course where id = :id", Course.class);
        query.setParameter("id", 2);
        List resultList = query.getResultList();
        logger.info("select * from course where id = :id -> {}", resultList);
    }

    @Test
    @Transactional // To avoid transactional required exception
    public void native_query_update() {
        Query query = entityManager.createNativeQuery("update course set name = 'Go :=' where id = :id", Course.class);
        query.setParameter("id", 2);
        int rowsAffected = query.executeUpdate();
        logger.info("update course set name = 'Go :=' where id = :id - Rows updated -> {}", rowsAffected);
    }


    @Test
    // Criteria query does not use JPQL.
    public void basic_criteria_query(){
        // 1. Use criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        // 2. define roots for tables which are involved in the query
        Root<Course> courseRoot = query.from(Course.class);
        // 3. Define predicates etc using Criteria builder
        TypedQuery<Course> queryTyped = entityManager.createQuery(query.select(courseRoot));
        List<Course> resultList = queryTyped.getResultList();
        logger.info("Typed query -> {}", resultList);


    }

    @Test
    public void filter_criteria_query(){
        // 1. Use criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        // 2. define roots for tables which are involved in the query
        Root<Course> courseRoot = query.from(Course.class);
        // 3. Define predicates etc using Criteria builder
        Predicate like = cb.like(courseRoot.get("name"), "%Java");
        query.where(like);

        TypedQuery<Course> queryTyped = entityManager.createQuery(query.select(courseRoot));
        List<Course> resultList = queryTyped.getResultList();
        logger.info("Typed query -> {}", resultList);
    }

    @Test
    public void filter_multiple_tables_criteria_query(){
        // 1. Use criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        // 2. define roots for tables which are involved in the query
        Root<Course> courseRoot = query.from(Course.class);
        // 3. Define predicates etc using Criteria builder
        Predicate empty = cb.isEmpty(courseRoot.get("persons"));
        query.where(empty);

        TypedQuery<Course> queryTyped = entityManager.createQuery(query.select(courseRoot));
        List<Course> resultList = queryTyped.getResultList();
        logger.info("Typed query -> {}", resultList);
    }

    @Test
    public void join_criteria_query(){
        // 1. Use criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        // 2. define roots for tables which are involved in the query
        Root<Course> courseRoot = query.from(Course.class);
        // 3. Define predicates etc using Criteria builder
        Join<Object, Object> join = courseRoot.join("persons");


        TypedQuery<Course> queryTyped = entityManager.createQuery(query.select(courseRoot));
        List<Course> resultList = queryTyped.getResultList();
        logger.info("Typed query -> {}", resultList);
    }

    @Test
    public void left_join_criteria_query(){
        // 1. Use criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        // 2. define roots for tables which are involved in the query
        Root<Course> courseRoot = query.from(Course.class);
        // 3. Define predicates etc using Criteria builder
        Join<Object, Object> join = courseRoot.join("persons", JoinType.LEFT);


        TypedQuery<Course> queryTyped = entityManager.createQuery(query.select(courseRoot));
        List<Course> resultList = queryTyped.getResultList();
        logger.info("Typed query -> {}", resultList);
    }




}
