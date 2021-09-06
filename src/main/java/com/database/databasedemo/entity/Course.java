package com.database.databasedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {@NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
        @NamedQuery(name = "query_get_like", query = "select c from Course c where name like '%av%'")}) // Set a lot of named queries
//@NamedQuery(name = "query_get_all_courses", query = "select c from Course c") // Create query into entity only one
public class Course {
    @Id
    @GeneratedValue // Auto incremental
    private long id;
    private String name;

    /// This will be used for JPA to create the specific bean, It is mandatory for JPA
    protected Course() {
    }

    public Course(String name) { // Hibernate will provide the id
        this.name = name;
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

    @OneToMany(mappedBy = "course") // It has always lazy fetching
    private List<Review> reviews = new ArrayList<>();
}
