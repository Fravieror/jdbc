package com.database.databasedemo.entity;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne // It has always eager fetching
    private Course course;

    private String description;
    private String rating;

    public Review() {
    }

    public Review(String description, String rating) {
        this.description = description;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
