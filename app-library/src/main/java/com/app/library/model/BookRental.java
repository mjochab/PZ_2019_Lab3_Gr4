package com.app.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class BookRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Date dateOfRental;

    @NotNull
    private Date dateOfReturn;

    @NotNull
    private short prolongationNumber;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "borrow_by_user", updatable = false)
    private User borrower;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "lend_by_user", updatable = false)
    private User lender;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "book_signature", updatable = false)
    private BookUnit bookUnit;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(name = "book_order_unit_id", updatable = false)
    private BookOrderUnit bookOrderUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(Date dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public short getProlongationNumber() { return prolongationNumber; }

    public void setProlongationNumber(short prolongationNumber) { this.prolongationNumber = prolongationNumber; }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public User getLender() {
        return lender;
    }

    public void setLender(User lender) {
        this.lender = lender;
    }

    public BookUnit getBookUnit() {
        return bookUnit;
    }

    public void setBookUnit(BookUnit bookUnit) {
        this.bookUnit = bookUnit;
    }

    public BookOrderUnit getBookOrderUnit() {
        return bookOrderUnit;
    }

    public void setBookOrderUnit(BookOrderUnit bookOrderUnit) {
        this.bookOrderUnit = bookOrderUnit;
    }
}
