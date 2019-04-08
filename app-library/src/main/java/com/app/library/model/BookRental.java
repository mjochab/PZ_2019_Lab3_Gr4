package com.app.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class BookRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private Date dateOfRental;

    @Column
    @NotNull
    private Date dateOfReturn;

    @Column
    @NotNull
    private Byte prolongationNumber;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "borrow_by_user", updatable = false)
    private User borrower;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "lend_by_user", updatable = false)
    private User lender;


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

    public Byte getProlongationNumber() {
        return prolongationNumber;
    }

    public void setProlongationNumber(Byte prolongationNumber) {
        this.prolongationNumber = prolongationNumber;
    }

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




}
