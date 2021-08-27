package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Course;
import com.database.databasedemo.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional // Spring make easy to manage transaction
public class PersonJpaRepository {

    //connect to the database
    @PersistenceContext // It stores all operation
    EntityManager entityManager;// It is the interface to the persistence context, It creates a session to perform action into the database All operation
    // All the operation is going through the entity manager.
    public Person findByID(int id) {
        return entityManager.find(Person.class, id);
    }
    public Person update(Person person){
        return entityManager.merge(person); // If exist the Id into person update if not insert
    }
    public void deleteByID(int id){
        Person person = findByID(id);
        entityManager.remove(person);
    }
    public List<Person> findAll() {
         TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
         return namedQuery.getResultList();
    }

    public void playWithEntityManager(){
        Course course1 = new Course("ReactJs");
        entityManager.persist(course1);
        Course course2 = new Course("NodeJS");
        entityManager.persist(course2);

        entityManager.flush(); // This line send an execution to the DB

        entityManager.detach(course2); // This line ignore the entity received as parameter for the next operation into the method

        course1.setName("ReactJs - updated");
        entityManager.flush();

        entityManager.clear(); // This will ignore every change over entity before this line. Anything will not be tracked by entityManager

        course2.setName("NodeJS - updated"); // this both lines will not do anything because detach excluded they from changes
        entityManager.flush();

        course1.setName("ReactJs - updated second time");
        entityManager.flush();
    }
}
