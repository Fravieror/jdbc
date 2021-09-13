package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository // This defines that this class is a repository
@Transactional // This adds a transaction since beginning until end of every public method execution
public class CourseRepository {
    @Autowired // Auto instantiate the bean
    EntityManager entityManager; // Manage the bean instantiated.

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(Long id){
        Course course = findById(id);
        entityManager.remove(course);
    }


}