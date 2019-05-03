package com.app.library.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "book_unit")
public class BookUnit {

    @Id
    @GeneratedValue
    @Column(length = 64)
    private UUID signature;

    @Column(name = "checked_out")
    @NotNull
    private boolean checkedOut;

    @ManyToOne(optional = false)
    @NotNull
    private Book book;

    public BookUnit() {
    }

    public UUID getSignature() {
        return signature;
    }

    public void setSignature(UUID signature) {
        this.signature = signature;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
