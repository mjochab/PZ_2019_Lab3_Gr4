package com.app.library.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book_unit")
public class BookUnit {


    @Id
    @NotBlank
    @Length(min = 1, max = 64)
    @Column(unique = true)
    private String signature;

    @Column(name = "checked_out")
    @NotNull
    private boolean checkedOut;

    @ManyToOne(optional = false)
    @NotNull
    private Book book;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
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
