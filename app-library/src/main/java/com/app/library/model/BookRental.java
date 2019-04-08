package com.app.library.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class BookRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_of_rental")
    @NotBlank
    private Date date_of_rental;

    @Column(name = "date_of_return")
    @NotBlank
    private Date date_of_return;

    @ManyToOne
    @Column(name = "prolongation_number")
    @NotBlank
    private Byte prolongation_number;

    @ManyToOne(optional = false)
    private String borrow_by_user;

    @ManyToOne(optional = false)
    private String lend_by_user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer book_unit_id;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate_of_rental() {
        return date_of_rental;
    }

    public void setDate_of_rental(Date date_of_rental) {
        this.date_of_rental = date_of_rental;
    }

    public Date getDate_of_return() {
        return date_of_return;
    }

    public void setDate_of_return(Date date_of_return) {
        this.date_of_return = date_of_return;
    }

    public Byte getProlongation_number() {
        return prolongation_number;
    }

    public void setProlongation_number(Byte prolongation_number) {
        this.prolongation_number = prolongation_number;
    }

    public String getBorrow_by_user() {
        return borrow_by_user;
    }

    public void setBorrow_by_user(String borrow_by_user) {
        this.borrow_by_user = borrow_by_user;
    }

    public String getLend_by_user() {
        return lend_by_user;
    }

    public void setLend_by_user(String lend_by_user) {
        this.lend_by_user = lend_by_user;
    }

    public Integer getBook_unit_id() {
        return book_unit_id;
    }

    public void setBook_unit_id(Integer book_unit_id) {
        this.book_unit_id = book_unit_id;
    }




}
