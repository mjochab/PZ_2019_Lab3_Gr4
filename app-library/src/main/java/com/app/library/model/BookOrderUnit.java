package com.app.library.model;

import javafx.scene.control.CheckBox;

import javax.persistence.*;

@Entity
public class BookOrderUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean readyToRent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BooksOrder booksOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_signature")
    private BookUnit bookUnit;


    public BookOrderUnit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isReadyToRent() {
        return readyToRent;
    }

    public void setReadyToRent(boolean readyToRent) {
        this.readyToRent = readyToRent;
    }

    public BooksOrder getBooksOrder() {
        return booksOrder;
    }

    public void setBooksOrder(BooksOrder booksOrder) {
        this.booksOrder = booksOrder;
    }

    public BookUnit getBookUnit() {
        return bookUnit;
    }

    public void setBookUnit(BookUnit bookUnit) {
        this.bookUnit = bookUnit;
    }



    @Override
    public String toString() {
        return "BookOrderUnit{" +
                "id=" + id +
                ", readyToRent=" + readyToRent +
                ", booksOrder=" + booksOrder +
                ", bookUnit=" + bookUnit +
                '}';
    }
}
