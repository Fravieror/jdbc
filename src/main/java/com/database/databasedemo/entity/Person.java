package com.database.databasedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // When you are using in-memory database it automatically creates this key for us.
@Table(name="person") // This annotation indicates what is the name of the table in the DB.
// This is not necessary if tha table name matches with the name of class.
@NamedQuery(name = "find_all_persons", query = "select p from Person p") // Special syntax's hibernate.
public class Person {
    @Id // this is necessary to indicate that is Primary key
    @GeneratedValue // Autogenerate the ID
    private int id;
    @Column(name="name") // It indicates the name of the column, this is not necessary as well because the name matches
    private String name;
    private String location;
    private Date birthDate;
    @OneToOne(fetch=FetchType.LAZY)
    private Passport passport;
    @ManyToMany
    @JoinTable(name = "PERSON_COURSE", // Name that JPA will use to create the table
            joinColumns = @JoinColumn(name = "PERSON_ID"), // It gives the name to the column provenience from this table.
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID") // It gives the name to the column from the other table in join
    )
    private List<Course> courses = new ArrayList<>();

    @Embedded
    private Address address;

    //You need a constructor without arguments
    public Person() {
    }

    // This constructor allows creates a person with ID autogenerated by Hibernate
    public Person(String name, String location, Date birthDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public Person(int id, String name, String location, Date birthDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourses(Course course) {
        this.courses.add(course);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
