package com.app.library.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class BooksOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private Date createdAt;

    private boolean readyToRelease;

    @ManyToOne(optional = false)
    private User user;

    public BooksOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isReadyToRelease() {
        return readyToRelease;
    }

    public void setReadyToRelease(boolean readyToRelease) {
        this.readyToRelease = readyToRelease;
    }

    public User getReader() {
        return user;
    }

    public void setReader(User reader) {
        this.user = reader;
    }

    @Override
    public String toString() {
        return "BooksOrder{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", readyToRelease=" + readyToRelease +
                '}';
    }
}
