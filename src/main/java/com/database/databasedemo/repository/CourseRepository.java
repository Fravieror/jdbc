package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository // This defines that this class is a repository
public class CourseRepository {
    @Autowired // Auto instantiate the bean
    EntityManager entityManager; // Manage the bean instantiated.

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }


}