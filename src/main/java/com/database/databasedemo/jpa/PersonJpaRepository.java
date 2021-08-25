package com.database.databasedemo.jpa;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.jdbc.PersonDAO;
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
}
