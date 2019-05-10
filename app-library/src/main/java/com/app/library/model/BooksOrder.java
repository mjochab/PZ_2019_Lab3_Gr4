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

    @OneToMany(mappedBy = "booksOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BookOrderUnit> bookOrderUnits;

    @ManyToOne(optional = false)
    private User reader;

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

    public Set<BookOrderUnit> getBookOrderUnits() {
        return bookOrderUnits;
    }

    public void setBookOrderUnits(Set<BookOrderUnit> bookOrderUnits) {
        this.bookOrderUnits = bookOrderUnits;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
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
