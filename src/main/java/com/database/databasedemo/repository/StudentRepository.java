package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Course;
import com.database.databasedemo.entity.Passport;
import com.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@Repository // This defines that this class is a repository
@Transactional // This adds a transaction since beginning until end of every public method execution
public class StudentRepository {
    @Autowired // Auto instantiate the bean
    EntityManager entityManager; // Manage the bean instantiated.

    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }

    public void deleteById(Long id){
        Person person = findById(id);
        entityManager.remove(person);
    }

    public void saveStudentWithPassport(){
        Passport passport = new Passport("1030575570");
        entityManager.persist(passport);
        Person person = new Person("Mike", "Birmingham", new Date());
        person.setPassport(passport);
        entityManager.persist(person);
    }

    // Way to insert in table relational many to many
    public void insertStudentAndCourse(){
        Person person = new Person("Santiago", "Bogota", new Date());
        Course course = new Course("C#");
        entityManager.persist(person);
        entityManager.persist(course);

        person.addCourses(course);
        course.addPersons(person);

        entityManager.persist(person);
    }
}