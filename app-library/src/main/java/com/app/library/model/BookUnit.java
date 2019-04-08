package com.app.library.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class BookUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Length(min = 1, max = 64)
    @Column(unique = true)
    private String signature;

    @Column(name = "checked_out")
    @NotBlank
    @Length(min = 1, max = 1)
    private byte checkedOut;

    @ManyToOne(optional = false, targetEntity = Book.class)
    @NotBlank
    private int book_id;





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public byte getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(byte checkedOut) {
        this.checkedOut = checkedOut;
    }

    public int getBookId() {
        return book_id;
    }

    public void setBookId(int bookId) {
        this.book_id = bookId;
    }
}
