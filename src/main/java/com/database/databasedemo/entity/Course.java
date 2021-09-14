package com.database.databasedemo.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {@NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
        @NamedQuery(name = "query_get_like", query = "select c from Course c where name like '%av%'")}) // Set a lot of named queries
//@NamedQuery(name = "query_get_all_courses", query = "select c from Course c") // Create query into entity only one
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id=?") // SOFT deleted, It does not apply to native queries
@Where(clause = "is_deleted = false") // With a select this only retrieve files when is_deleted = false,  It does not apply to native queries
public class Course {

    private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue // Auto incremental
    private long id;
    private String name;
    public Course(String name) { // Hibernate will provide the id
        this.name = name;
    }

    @OneToMany(mappedBy = "course") // It has always lazy fetching
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")// this become the entity in the owning side, with this only will generate one table
    // to the relationship because if anyone of the entities is the owner side then JPA will create two tables.
    private List<Person> persons = new ArrayList<>();

    private boolean isDeleted;

    @PreRemove
    private void preRemove(){
        LOGGER.info("Setting isDeleted to true");
        this.isDeleted = true;
    }

    /// This will be used for JPA to create the specific bean, It is mandatory for JPA
    protected Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPersons(Person person) {
        this.persons.add(person);
    }
}
